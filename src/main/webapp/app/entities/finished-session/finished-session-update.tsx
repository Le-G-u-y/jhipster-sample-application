import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IPlan } from 'app/shared/model/plan.model';
import { getEntities as getPlans } from 'app/entities/plan/plan.reducer';
import { getEntity, updateEntity, createEntity, reset } from './finished-session.reducer';
import { IFinishedSession } from 'app/shared/model/finished-session.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IFinishedSessionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IFinishedSessionUpdateState {
  isNew: boolean;
  planId: string;
}

export class FinishedSessionUpdate extends React.Component<IFinishedSessionUpdateProps, IFinishedSessionUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      planId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getPlans();
  }

  saveEntity = (event, errors, values) => {
    values.createDate = convertDateTimeToServer(values.createDate);
    values.modifyDate = convertDateTimeToServer(values.modifyDate);

    if (errors.length === 0) {
      const { finishedSessionEntity } = this.props;
      const entity = {
        ...finishedSessionEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/finished-session');
  };

  render() {
    const { finishedSessionEntity, plans, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.finishedSession.home.createOrEditLabel">
              <Translate contentKey="jhipsterSampleApplicationApp.finishedSession.home.createOrEditLabel">
                Create or edit a FinishedSession
              </Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : finishedSessionEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="finished-session-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="finished-session-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="minutesTotalLabel" for="finished-session-minutesTotal">
                    <Translate contentKey="jhipsterSampleApplicationApp.finishedSession.minutesTotal">Minutes Total</Translate>
                  </Label>
                  <AvField
                    id="finished-session-minutesTotal"
                    type="string"
                    className="form-control"
                    name="minutesTotal"
                    validate={{
                      min: { value: 1, errorMessage: translate('entity.validation.min', { min: 1 }) },
                      max: { value: 600, errorMessage: translate('entity.validation.max', { max: 600 }) },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="noteLabel" for="finished-session-note">
                    <Translate contentKey="jhipsterSampleApplicationApp.finishedSession.note">Note</Translate>
                  </Label>
                  <AvField
                    id="finished-session-note"
                    type="text"
                    name="note"
                    validate={{
                      maxLength: { value: 30000, errorMessage: translate('entity.validation.maxlength', { max: 30000 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="createDateLabel" for="finished-session-createDate">
                    <Translate contentKey="jhipsterSampleApplicationApp.finishedSession.createDate">Create Date</Translate>
                  </Label>
                  <AvInput
                    id="finished-session-createDate"
                    type="datetime-local"
                    className="form-control"
                    name="createDate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.finishedSessionEntity.createDate)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="modifyDateLabel" for="finished-session-modifyDate">
                    <Translate contentKey="jhipsterSampleApplicationApp.finishedSession.modifyDate">Modify Date</Translate>
                  </Label>
                  <AvInput
                    id="finished-session-modifyDate"
                    type="datetime-local"
                    className="form-control"
                    name="modifyDate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.finishedSessionEntity.modifyDate)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="finished-session-plan">
                    <Translate contentKey="jhipsterSampleApplicationApp.finishedSession.plan">Plan</Translate>
                  </Label>
                  <AvInput id="finished-session-plan" type="select" className="form-control" name="planId">
                    <option value="" key="0" />
                    {plans
                      ? plans.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/finished-session" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  plans: storeState.plan.entities,
  finishedSessionEntity: storeState.finishedSession.entity,
  loading: storeState.finishedSession.loading,
  updating: storeState.finishedSession.updating,
  updateSuccess: storeState.finishedSession.updateSuccess
});

const mapDispatchToProps = {
  getPlans,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FinishedSessionUpdate);
