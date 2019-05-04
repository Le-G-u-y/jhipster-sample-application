import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IRole } from 'app/shared/model/role.model';
import { getEntities as getRoles } from 'app/entities/role/role.reducer';
import { IDrummerStatistics } from 'app/shared/model/drummer-statistics.model';
import { getEntities as getDrummerStatistics } from 'app/entities/drummer-statistics/drummer-statistics.reducer';
import { getEntity, updateEntity, createEntity, reset } from './drummer.reducer';
import { IDrummer } from 'app/shared/model/drummer.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDrummerUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IDrummerUpdateState {
  isNew: boolean;
  roleId: string;
  drummerStatisticsId: string;
}

export class DrummerUpdate extends React.Component<IDrummerUpdateProps, IDrummerUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      roleId: '0',
      drummerStatisticsId: '0',
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

    this.props.getRoles();
    this.props.getDrummerStatistics();
  }

  saveEntity = (event, errors, values) => {
    values.createDate = convertDateTimeToServer(values.createDate);
    values.modifyDate = convertDateTimeToServer(values.modifyDate);

    if (errors.length === 0) {
      const { drummerEntity } = this.props;
      const entity = {
        ...drummerEntity,
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
    this.props.history.push('/entity/drummer');
  };

  render() {
    const { drummerEntity, roles, drummerStatistics, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.drummer.home.createOrEditLabel">
              <Translate contentKey="jhipsterSampleApplicationApp.drummer.home.createOrEditLabel">Create or edit a Drummer</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : drummerEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="drummer-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="drummer-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="drummerNameLabel" for="drummer-drummerName">
                    <Translate contentKey="jhipsterSampleApplicationApp.drummer.drummerName">Drummer Name</Translate>
                  </Label>
                  <AvField
                    id="drummer-drummerName"
                    type="text"
                    name="drummerName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="descriptionLabel" for="drummer-description">
                    <Translate contentKey="jhipsterSampleApplicationApp.drummer.description">Description</Translate>
                  </Label>
                  <AvField id="drummer-description" type="text" name="description" />
                </AvGroup>
                <AvGroup>
                  <Label id="emailLabel" for="drummer-email">
                    <Translate contentKey="jhipsterSampleApplicationApp.drummer.email">Email</Translate>
                  </Label>
                  <AvField
                    id="drummer-email"
                    type="text"
                    name="email"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="createDateLabel" for="drummer-createDate">
                    <Translate contentKey="jhipsterSampleApplicationApp.drummer.createDate">Create Date</Translate>
                  </Label>
                  <AvInput
                    id="drummer-createDate"
                    type="datetime-local"
                    className="form-control"
                    name="createDate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.drummerEntity.createDate)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="modifyDateLabel" for="drummer-modifyDate">
                    <Translate contentKey="jhipsterSampleApplicationApp.drummer.modifyDate">Modify Date</Translate>
                  </Label>
                  <AvInput
                    id="drummer-modifyDate"
                    type="datetime-local"
                    className="form-control"
                    name="modifyDate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.drummerEntity.modifyDate)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="drummer-role">
                    <Translate contentKey="jhipsterSampleApplicationApp.drummer.role">Role</Translate>
                  </Label>
                  <AvInput id="drummer-role" type="select" className="form-control" name="roleId">
                    <option value="" key="0" />
                    {roles
                      ? roles.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/drummer" replace color="info">
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
  roles: storeState.role.entities,
  drummerStatistics: storeState.drummerStatistics.entities,
  drummerEntity: storeState.drummer.entity,
  loading: storeState.drummer.loading,
  updating: storeState.drummer.updating,
  updateSuccess: storeState.drummer.updateSuccess
});

const mapDispatchToProps = {
  getRoles,
  getDrummerStatistics,
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
)(DrummerUpdate);
