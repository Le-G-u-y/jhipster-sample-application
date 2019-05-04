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
import { getEntity, updateEntity, createEntity, reset } from './drummer-statistics.reducer';
import { IDrummerStatistics } from 'app/shared/model/drummer-statistics.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDrummerStatisticsUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IDrummerStatisticsUpdateState {
  isNew: boolean;
  drummerId: string;
}

export class DrummerStatisticsUpdate extends React.Component<IDrummerStatisticsUpdateProps, IDrummerStatisticsUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      drummerId: '0',
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
  }

  saveEntity = (event, errors, values) => {
    values.createDate = convertDateTimeToServer(values.createDate);
    values.modifyDate = convertDateTimeToServer(values.modifyDate);

    if (errors.length === 0) {
      const { drummerStatisticsEntity } = this.props;
      const entity = {
        ...drummerStatisticsEntity,
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
    this.props.history.push('/entity/drummer-statistics');
  };

  render() {
    const { drummerStatisticsEntity, drummers, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.drummerStatistics.home.createOrEditLabel">
              <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.home.createOrEditLabel">
                Create or edit a DrummerStatistics
              </Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : drummerStatisticsEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="drummer-statistics-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="drummer-statistics-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="selfAssessedLevelSpeedLabel" for="drummer-statistics-selfAssessedLevelSpeed">
                    <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.selfAssessedLevelSpeed">
                      Self Assessed Level Speed
                    </Translate>
                  </Label>
                  <AvField
                    id="drummer-statistics-selfAssessedLevelSpeed"
                    type="string"
                    className="form-control"
                    name="selfAssessedLevelSpeed"
                    validate={{
                      min: { value: 0, errorMessage: translate('entity.validation.min', { min: 0 }) },
                      max: { value: 10, errorMessage: translate('entity.validation.max', { max: 10 }) },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="selfAssessedLevelGrooveLabel" for="drummer-statistics-selfAssessedLevelGroove">
                    <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.selfAssessedLevelGroove">
                      Self Assessed Level Groove
                    </Translate>
                  </Label>
                  <AvField
                    id="drummer-statistics-selfAssessedLevelGroove"
                    type="string"
                    className="form-control"
                    name="selfAssessedLevelGroove"
                    validate={{
                      min: { value: 0, errorMessage: translate('entity.validation.min', { min: 0 }) },
                      max: { value: 10, errorMessage: translate('entity.validation.max', { max: 10 }) },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="selfAssessedLevelCreativityLabel" for="drummer-statistics-selfAssessedLevelCreativity">
                    <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.selfAssessedLevelCreativity">
                      Self Assessed Level Creativity
                    </Translate>
                  </Label>
                  <AvField
                    id="drummer-statistics-selfAssessedLevelCreativity"
                    type="string"
                    className="form-control"
                    name="selfAssessedLevelCreativity"
                    validate={{
                      min: { value: 0, errorMessage: translate('entity.validation.min', { min: 0 }) },
                      max: { value: 10, errorMessage: translate('entity.validation.max', { max: 10 }) },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="selfAssessedLevelAdaptabilityLabel" for="drummer-statistics-selfAssessedLevelAdaptability">
                    <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.selfAssessedLevelAdaptability">
                      Self Assessed Level Adaptability
                    </Translate>
                  </Label>
                  <AvField
                    id="drummer-statistics-selfAssessedLevelAdaptability"
                    type="string"
                    className="form-control"
                    name="selfAssessedLevelAdaptability"
                    validate={{
                      min: { value: 0, errorMessage: translate('entity.validation.min', { min: 0 }) },
                      max: { value: 10, errorMessage: translate('entity.validation.max', { max: 10 }) },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="selfAssessedLevelDynamicsLabel" for="drummer-statistics-selfAssessedLevelDynamics">
                    <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.selfAssessedLevelDynamics">
                      Self Assessed Level Dynamics
                    </Translate>
                  </Label>
                  <AvField
                    id="drummer-statistics-selfAssessedLevelDynamics"
                    type="string"
                    className="form-control"
                    name="selfAssessedLevelDynamics"
                    validate={{
                      min: { value: 0, errorMessage: translate('entity.validation.min', { min: 0 }) },
                      max: { value: 10, errorMessage: translate('entity.validation.max', { max: 10 }) },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="selfAssessedLevelIndependenceLabel" for="drummer-statistics-selfAssessedLevelIndependence">
                    <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.selfAssessedLevelIndependence">
                      Self Assessed Level Independence
                    </Translate>
                  </Label>
                  <AvField
                    id="drummer-statistics-selfAssessedLevelIndependence"
                    type="string"
                    className="form-control"
                    name="selfAssessedLevelIndependence"
                    validate={{
                      min: { value: 0, errorMessage: translate('entity.validation.min', { min: 0 }) },
                      max: { value: 10, errorMessage: translate('entity.validation.max', { max: 10 }) },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="selfAssessedLevelLivePerformanceLabel" for="drummer-statistics-selfAssessedLevelLivePerformance">
                    <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.selfAssessedLevelLivePerformance">
                      Self Assessed Level Live Performance
                    </Translate>
                  </Label>
                  <AvField
                    id="drummer-statistics-selfAssessedLevelLivePerformance"
                    type="string"
                    className="form-control"
                    name="selfAssessedLevelLivePerformance"
                    validate={{
                      min: { value: 0, errorMessage: translate('entity.validation.min', { min: 0 }) },
                      max: { value: 10, errorMessage: translate('entity.validation.max', { max: 10 }) },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="selfAssessedLevelReadingMusicLabel" for="drummer-statistics-selfAssessedLevelReadingMusic">
                    <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.selfAssessedLevelReadingMusic">
                      Self Assessed Level Reading Music
                    </Translate>
                  </Label>
                  <AvField
                    id="drummer-statistics-selfAssessedLevelReadingMusic"
                    type="string"
                    className="form-control"
                    name="selfAssessedLevelReadingMusic"
                    validate={{
                      min: { value: 0, errorMessage: translate('entity.validation.min', { min: 0 }) },
                      max: { value: 10, errorMessage: translate('entity.validation.max', { max: 10 }) },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="createDateLabel" for="drummer-statistics-createDate">
                    <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.createDate">Create Date</Translate>
                  </Label>
                  <AvInput
                    id="drummer-statistics-createDate"
                    type="datetime-local"
                    className="form-control"
                    name="createDate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.drummerStatisticsEntity.createDate)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="modifyDateLabel" for="drummer-statistics-modifyDate">
                    <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.modifyDate">Modify Date</Translate>
                  </Label>
                  <AvInput
                    id="drummer-statistics-modifyDate"
                    type="datetime-local"
                    className="form-control"
                    name="modifyDate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.drummerStatisticsEntity.modifyDate)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="drummer-statistics-drummer">
                    <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.drummer">Drummer</Translate>
                  </Label>
                  <AvInput id="drummer-statistics-drummer" type="select" className="form-control" name="drummerId">
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
                <Button tag={Link} id="cancel-save" to="/entity/drummer-statistics" replace color="info">
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
  drummerStatisticsEntity: storeState.drummerStatistics.entity,
  loading: storeState.drummerStatistics.loading,
  updating: storeState.drummerStatistics.updating,
  updateSuccess: storeState.drummerStatistics.updateSuccess
});

const mapDispatchToProps = {
  getDrummers,
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
)(DrummerStatisticsUpdate);
