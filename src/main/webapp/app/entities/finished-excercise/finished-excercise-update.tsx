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
import { IFinishedSession } from 'app/shared/model/finished-session.model';
import { getEntities as getFinishedSessions } from 'app/entities/finished-session/finished-session.reducer';
import { getEntity, updateEntity, createEntity, reset } from './finished-excercise.reducer';
import { IFinishedExcercise } from 'app/shared/model/finished-excercise.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IFinishedExcerciseUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IFinishedExcerciseUpdateState {
  isNew: boolean;
  excerciseId: string;
  finishedSessionId: string;
}

export class FinishedExcerciseUpdate extends React.Component<IFinishedExcerciseUpdateProps, IFinishedExcerciseUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      excerciseId: '0',
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

    this.props.getExcercises();
    this.props.getFinishedSessions();
  }

  saveEntity = (event, errors, values) => {
    values.createDate = convertDateTimeToServer(values.createDate);
    values.modifyDate = convertDateTimeToServer(values.modifyDate);

    if (errors.length === 0) {
      const { finishedExcerciseEntity } = this.props;
      const entity = {
        ...finishedExcerciseEntity,
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
    this.props.history.push('/entity/finished-excercise');
  };

  render() {
    const { finishedExcerciseEntity, excercises, finishedSessions, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.finishedExcercise.home.createOrEditLabel">
              <Translate contentKey="jhipsterSampleApplicationApp.finishedExcercise.home.createOrEditLabel">
                Create or edit a FinishedExcercise
              </Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : finishedExcerciseEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="finished-excercise-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="finished-excercise-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="actualBpmLabel" for="finished-excercise-actualBpm">
                    <Translate contentKey="jhipsterSampleApplicationApp.finishedExcercise.actualBpm">Actual Bpm</Translate>
                  </Label>
                  <AvField
                    id="finished-excercise-actualBpm"
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
                  <Label id="actualMinutesLabel" for="finished-excercise-actualMinutes">
                    <Translate contentKey="jhipsterSampleApplicationApp.finishedExcercise.actualMinutes">Actual Minutes</Translate>
                  </Label>
                  <AvField
                    id="finished-excercise-actualMinutes"
                    type="string"
                    className="form-control"
                    name="actualMinutes"
                    validate={{
                      min: { value: 1, errorMessage: translate('entity.validation.min', { min: 1 }) },
                      max: { value: 600, errorMessage: translate('entity.validation.max', { max: 600 }) },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="createDateLabel" for="finished-excercise-createDate">
                    <Translate contentKey="jhipsterSampleApplicationApp.finishedExcercise.createDate">Create Date</Translate>
                  </Label>
                  <AvInput
                    id="finished-excercise-createDate"
                    type="datetime-local"
                    className="form-control"
                    name="createDate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.finishedExcerciseEntity.createDate)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="modifyDateLabel" for="finished-excercise-modifyDate">
                    <Translate contentKey="jhipsterSampleApplicationApp.finishedExcercise.modifyDate">Modify Date</Translate>
                  </Label>
                  <AvInput
                    id="finished-excercise-modifyDate"
                    type="datetime-local"
                    className="form-control"
                    name="modifyDate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.finishedExcerciseEntity.modifyDate)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="finished-excercise-excercise">
                    <Translate contentKey="jhipsterSampleApplicationApp.finishedExcercise.excercise">Excercise</Translate>
                  </Label>
                  <AvInput id="finished-excercise-excercise" type="select" className="form-control" name="excerciseId">
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
                  <Label for="finished-excercise-finishedSession">
                    <Translate contentKey="jhipsterSampleApplicationApp.finishedExcercise.finishedSession">Finished Session</Translate>
                  </Label>
                  <AvInput id="finished-excercise-finishedSession" type="select" className="form-control" name="finishedSessionId">
                    <option value="" key="0" />
                    {finishedSessions
                      ? finishedSessions.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/finished-excercise" replace color="info">
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
  finishedSessions: storeState.finishedSession.entities,
  finishedExcerciseEntity: storeState.finishedExcercise.entity,
  loading: storeState.finishedExcercise.loading,
  updating: storeState.finishedExcercise.updating,
  updateSuccess: storeState.finishedExcercise.updateSuccess
});

const mapDispatchToProps = {
  getExcercises,
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
)(FinishedExcerciseUpdate);
