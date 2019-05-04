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
import { getEntity, updateEntity, createEntity, reset } from './excercise.reducer';
import { IExcercise } from 'app/shared/model/excercise.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IExcerciseUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IExcerciseUpdateState {
  isNew: boolean;
  creatorId: string;
}

export class ExcerciseUpdate extends React.Component<IExcerciseUpdateProps, IExcerciseUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      creatorId: '0',
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
      const { excerciseEntity } = this.props;
      const entity = {
        ...excerciseEntity,
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
    this.props.history.push('/entity/excercise');
  };

  render() {
    const { excerciseEntity, drummers, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.excercise.home.createOrEditLabel">
              <Translate contentKey="jhipsterSampleApplicationApp.excercise.home.createOrEditLabel">Create or edit a Excercise</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : excerciseEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="excercise-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="excercise-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="excerciseNameLabel" for="excercise-excerciseName">
                    <Translate contentKey="jhipsterSampleApplicationApp.excercise.excerciseName">Excercise Name</Translate>
                  </Label>
                  <AvField
                    id="excercise-excerciseName"
                    type="text"
                    name="excerciseName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 200, errorMessage: translate('entity.validation.maxlength', { max: 200 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="descriptionLabel" for="excercise-description">
                    <Translate contentKey="jhipsterSampleApplicationApp.excercise.description">Description</Translate>
                  </Label>
                  <AvField
                    id="excercise-description"
                    type="text"
                    name="description"
                    validate={{
                      maxLength: { value: 30000, errorMessage: translate('entity.validation.maxlength', { max: 30000 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="defaultMinutesLabel" for="excercise-defaultMinutes">
                    <Translate contentKey="jhipsterSampleApplicationApp.excercise.defaultMinutes">Default Minutes</Translate>
                  </Label>
                  <AvField
                    id="excercise-defaultMinutes"
                    type="string"
                    className="form-control"
                    name="defaultMinutes"
                    validate={{
                      max: { value: 9000, errorMessage: translate('entity.validation.max', { max: 9000 }) },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="defaultTargetBpmLabel" for="excercise-defaultTargetBpm">
                    <Translate contentKey="jhipsterSampleApplicationApp.excercise.defaultTargetBpm">Default Target Bpm</Translate>
                  </Label>
                  <AvField
                    id="excercise-defaultTargetBpm"
                    type="string"
                    className="form-control"
                    name="defaultTargetBpm"
                    validate={{
                      min: { value: 1, errorMessage: translate('entity.validation.min', { min: 1 }) },
                      max: { value: 500, errorMessage: translate('entity.validation.max', { max: 500 }) },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="skillTypeLabel" for="excercise-skillType">
                    <Translate contentKey="jhipsterSampleApplicationApp.excercise.skillType">Skill Type</Translate>
                  </Label>
                  <AvInput
                    id="excercise-skillType"
                    type="select"
                    className="form-control"
                    name="skillType"
                    value={(!isNew && excerciseEntity.skillType) || 'SPEED'}
                  >
                    <option value="SPEED">
                      <Translate contentKey="jhipsterSampleApplicationApp.SkillType.SPEED" />
                    </option>
                    <option value="GROOVE">
                      <Translate contentKey="jhipsterSampleApplicationApp.SkillType.GROOVE" />
                    </option>
                    <option value="CREATIVITY">
                      <Translate contentKey="jhipsterSampleApplicationApp.SkillType.CREATIVITY" />
                    </option>
                    <option value="ADAPTABILITY">
                      <Translate contentKey="jhipsterSampleApplicationApp.SkillType.ADAPTABILITY" />
                    </option>
                    <option value="DYNAMICS">
                      <Translate contentKey="jhipsterSampleApplicationApp.SkillType.DYNAMICS" />
                    </option>
                    <option value="INDEPENDENCE">
                      <Translate contentKey="jhipsterSampleApplicationApp.SkillType.INDEPENDENCE" />
                    </option>
                    <option value="LIVE_PERFORMANCE">
                      <Translate contentKey="jhipsterSampleApplicationApp.SkillType.LIVE_PERFORMANCE" />
                    </option>
                    <option value="READING_MUSIC">
                      <Translate contentKey="jhipsterSampleApplicationApp.SkillType.READING_MUSIC" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="excerciseTypeLabel" for="excercise-excerciseType">
                    <Translate contentKey="jhipsterSampleApplicationApp.excercise.excerciseType">Excercise Type</Translate>
                  </Label>
                  <AvInput
                    id="excercise-excerciseType"
                    type="select"
                    className="form-control"
                    name="excerciseType"
                    value={(!isNew && excerciseEntity.excerciseType) || 'RUDIMENT'}
                  >
                    <option value="RUDIMENT">
                      <Translate contentKey="jhipsterSampleApplicationApp.ExcerciseType.RUDIMENT" />
                    </option>
                    <option value="TECHNIQUE">
                      <Translate contentKey="jhipsterSampleApplicationApp.ExcerciseType.TECHNIQUE" />
                    </option>
                    <option value="SONG">
                      <Translate contentKey="jhipsterSampleApplicationApp.ExcerciseType.SONG" />
                    </option>
                    <option value="PLAYALONG">
                      <Translate contentKey="jhipsterSampleApplicationApp.ExcerciseType.PLAYALONG" />
                    </option>
                    <option value="SIGHT_READING">
                      <Translate contentKey="jhipsterSampleApplicationApp.ExcerciseType.SIGHT_READING" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="createDateLabel" for="excercise-createDate">
                    <Translate contentKey="jhipsterSampleApplicationApp.excercise.createDate">Create Date</Translate>
                  </Label>
                  <AvInput
                    id="excercise-createDate"
                    type="datetime-local"
                    className="form-control"
                    name="createDate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.excerciseEntity.createDate)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="modifyDateLabel" for="excercise-modifyDate">
                    <Translate contentKey="jhipsterSampleApplicationApp.excercise.modifyDate">Modify Date</Translate>
                  </Label>
                  <AvInput
                    id="excercise-modifyDate"
                    type="datetime-local"
                    className="form-control"
                    name="modifyDate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.excerciseEntity.modifyDate)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="excercise-creator">
                    <Translate contentKey="jhipsterSampleApplicationApp.excercise.creator">Creator</Translate>
                  </Label>
                  <AvInput id="excercise-creator" type="select" className="form-control" name="creatorId">
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
                <Button tag={Link} id="cancel-save" to="/entity/excercise" replace color="info">
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
  excerciseEntity: storeState.excercise.entity,
  loading: storeState.excercise.loading,
  updating: storeState.excercise.updating,
  updateSuccess: storeState.excercise.updateSuccess
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
)(ExcerciseUpdate);
