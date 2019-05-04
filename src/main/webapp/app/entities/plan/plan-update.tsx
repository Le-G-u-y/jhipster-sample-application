import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDrummer } from 'app/shared/model/drummer.model';
import { getEntities as getDrummers } from 'app/entities/drummer/drummer.reducer';
import { IFinishedSession } from 'app/shared/model/finished-session.model';
import { getEntities as getFinishedSessions } from 'app/entities/finished-session/finished-session.reducer';
import { getEntity, updateEntity, createEntity, reset } from './plan.reducer';
import { IPlan } from 'app/shared/model/plan.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPlanUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPlanUpdateState {
  isNew: boolean;
  creatorId: string;
  ownerId: string;
  finishedSessionId: string;
}

export class PlanUpdate extends React.Component<IPlanUpdateProps, IPlanUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      creatorId: '0',
      ownerId: '0',
      finishedSessionId: '0',
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

    this.props.getDrummers();
    this.props.getFinishedSessions();
  }

  saveEntity = (event, errors, values) => {
    values.targetDate = convertDateTimeToServer(values.targetDate);
    values.createDate = convertDateTimeToServer(values.createDate);
    values.modifyDate = convertDateTimeToServer(values.modifyDate);

    if (errors.length === 0) {
      const { planEntity } = this.props;
      const entity = {
        ...planEntity,
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
    this.props.history.push('/entity/plan');
  };

  render() {
    const { planEntity, drummers, finishedSessions, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.plan.home.createOrEditLabel">
              <Translate contentKey="jhipsterSampleApplicationApp.plan.home.createOrEditLabel">Create or edit a Plan</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : planEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="plan-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="plan-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="planNameLabel" for="plan-planName">
                    <Translate contentKey="jhipsterSampleApplicationApp.plan.planName">Plan Name</Translate>
                  </Label>
                  <AvField
                    id="plan-planName"
                    type="text"
                    name="planName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="planFocusLabel" for="plan-planFocus">
                    <Translate contentKey="jhipsterSampleApplicationApp.plan.planFocus">Plan Focus</Translate>
                  </Label>
                  <AvField
                    id="plan-planFocus"
                    type="text"
                    name="planFocus"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="descriptionLabel" for="plan-description">
                    <Translate contentKey="jhipsterSampleApplicationApp.plan.description">Description</Translate>
                  </Label>
                  <AvField id="plan-description" type="text" name="description" />
                </AvGroup>
                <AvGroup>
                  <Label id="minutesPerSessionLabel" for="plan-minutesPerSession">
                    <Translate contentKey="jhipsterSampleApplicationApp.plan.minutesPerSession">Minutes Per Session</Translate>
                  </Label>
                  <AvField id="plan-minutesPerSession" type="string" className="form-control" name="minutesPerSession" />
                </AvGroup>
                <AvGroup>
                  <Label id="sessionsPerWeekLabel" for="plan-sessionsPerWeek">
                    <Translate contentKey="jhipsterSampleApplicationApp.plan.sessionsPerWeek">Sessions Per Week</Translate>
                  </Label>
                  <AvField id="plan-sessionsPerWeek" type="string" className="form-control" name="sessionsPerWeek" />
                </AvGroup>
                <AvGroup>
                  <Label id="targetDateLabel" for="plan-targetDate">
                    <Translate contentKey="jhipsterSampleApplicationApp.plan.targetDate">Target Date</Translate>
                  </Label>
                  <AvInput
                    id="plan-targetDate"
                    type="datetime-local"
                    className="form-control"
                    name="targetDate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.planEntity.targetDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="createDateLabel" for="plan-createDate">
                    <Translate contentKey="jhipsterSampleApplicationApp.plan.createDate">Create Date</Translate>
                  </Label>
                  <AvInput
                    id="plan-createDate"
                    type="datetime-local"
                    className="form-control"
                    name="createDate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.planEntity.createDate)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="modifyDateLabel" for="plan-modifyDate">
                    <Translate contentKey="jhipsterSampleApplicationApp.plan.modifyDate">Modify Date</Translate>
                  </Label>
                  <AvInput
                    id="plan-modifyDate"
                    type="datetime-local"
                    className="form-control"
                    name="modifyDate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.planEntity.modifyDate)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="plan-creator">
                    <Translate contentKey="jhipsterSampleApplicationApp.plan.creator">Creator</Translate>
                  </Label>
                  <AvInput id="plan-creator" type="select" className="form-control" name="creatorId">
                    <option value="" key="0" />
                    {drummers
                      ? drummers.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="plan-owner">
                    <Translate contentKey="jhipsterSampleApplicationApp.plan.owner">Owner</Translate>
                  </Label>
                  <AvInput id="plan-owner" type="select" className="form-control" name="ownerId">
                    <option value="" key="0" />
                    {drummers
                      ? drummers.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/plan" replace color="info">
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
  drummers: storeState.drummer.entities,
  finishedSessions: storeState.finishedSession.entities,
  planEntity: storeState.plan.entity,
  loading: storeState.plan.loading,
  updating: storeState.plan.updating,
  updateSuccess: storeState.plan.updateSuccess
});

const mapDispatchToProps = {
  getDrummers,
  getFinishedSessions,
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
)(PlanUpdate);
