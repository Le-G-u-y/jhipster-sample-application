import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IExcercise } from 'app/shared/model/excercise.model';
import { getEntities as getExcercises } from 'app/entities/excercise/excercise.reducer';
import { IPlan } from 'app/shared/model/plan.model';
import { getEntities as getPlans } from 'app/entities/plan/plan.reducer';
import { getEntity, updateEntity, createEntity, reset } from './excercise-config.reducer';
import { IExcerciseConfig } from 'app/shared/model/excercise-config.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IExcerciseConfigUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IExcerciseConfigUpdateState {
  isNew: boolean;
  excerciseId: string;
  planId: string;
}

export class ExcerciseConfigUpdate extends React.Component<IExcerciseConfigUpdateProps, IExcerciseConfigUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      excerciseId: '0',
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

    this.props.getExcercises();
    this.props.getPlans();
  }

  saveEntity = (event, errors, values) => {
    values.createDate = convertDateTimeToServer(values.createDate);
    values.modifyDate = convertDateTimeToServer(values.modifyDate);

    if (errors.length === 0) {
      const { excerciseConfigEntity } = this.props;
      const entity = {
        ...excerciseConfigEntity,
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
    this.props.history.push('/entity/excercise-config');
  };

  render() {
    const { excerciseConfigEntity, excercises, plans, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.excerciseConfig.home.createOrEditLabel">
              <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.home.createOrEditLabel">
                Create or edit a ExcerciseConfig
              </Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : excerciseConfigEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="excercise-config-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="excercise-config-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="actualBpmLabel" for="excercise-config-actualBpm">
                    <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.actualBpm">Actual Bpm</Translate>
                  </Label>
                  <AvField
                    id="excercise-config-actualBpm"
                    type="string"
                    className="form-control"
                    name="actualBpm"
                    validate={{
                      min: { value: 1, errorMessage: translate('entity.validation.min', { min: 1 }) },
                      max: { value: 500, errorMessage: translate('entity.validation.max', { max: 500 }) },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="targetBpmLabel" for="excercise-config-targetBpm">
                    <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.targetBpm">Target Bpm</Translate>
                  </Label>
                  <AvField
                    id="excercise-config-targetBpm"
                    type="string"
                    className="form-control"
                    name="targetBpm"
                    validate={{
                      min: { value: 1, errorMessage: translate('entity.validation.min', { min: 1 }) },
                      max: { value: 500, errorMessage: translate('entity.validation.max', { max: 500 }) },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="minutesLabel" for="excercise-config-minutes">
                    <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.minutes">Minutes</Translate>
                  </Label>
                  <AvField
                    id="excercise-config-minutes"
                    type="string"
                    className="form-control"
                    name="minutes"
                    validate={{
                      min: { value: 1, errorMessage: translate('entity.validation.min', { min: 1 }) },
                      max: { value: 500, errorMessage: translate('entity.validation.max', { max: 500 }) },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="noteLabel" for="excercise-config-note">
                    <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.note">Note</Translate>
                  </Label>
                  <AvField
                    id="excercise-config-note"
                    type="text"
                    name="note"
                    validate={{
                      maxLength: { value: 30000, errorMessage: translate('entity.validation.maxlength', { max: 30000 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="createDateLabel" for="excercise-config-createDate">
                    <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.createDate">Create Date</Translate>
                  </Label>
                  <AvInput
                    id="excercise-config-createDate"
                    type="datetime-local"
                    className="form-control"
                    name="createDate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.excerciseConfigEntity.createDate)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="modifyDateLabel" for="excercise-config-modifyDate">
                    <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.modifyDate">Modify Date</Translate>
                  </Label>
                  <AvInput
                    id="excercise-config-modifyDate"
                    type="datetime-local"
                    className="form-control"
                    name="modifyDate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.excerciseConfigEntity.modifyDate)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="excercise-config-excercise">
                    <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.excercise">Excercise</Translate>
                  </Label>
                  <AvInput id="excercise-config-excercise" type="select" className="form-control" name="excerciseId">
                    <option value="" key="0" />
                    {excercises
                      ? excercises.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="excercise-config-plan">
                    <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.plan">Plan</Translate>
                  </Label>
                  <AvInput id="excercise-config-plan" type="select" className="form-control" name="planId">
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
                <Button tag={Link} id="cancel-save" to="/entity/excercise-config" replace color="info">
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
  excercises: storeState.excercise.entities,
  plans: storeState.plan.entities,
  excerciseConfigEntity: storeState.excerciseConfig.entity,
  loading: storeState.excerciseConfig.loading,
  updating: storeState.excerciseConfig.updating,
  updateSuccess: storeState.excerciseConfig.updateSuccess
});

const mapDispatchToProps = {
  getExcercises,
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
)(ExcerciseConfigUpdate);
