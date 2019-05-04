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
import { getEntity, updateEntity, createEntity, reset } from './permission.reducer';
import { IPermission } from 'app/shared/model/permission.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPermissionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPermissionUpdateState {
  isNew: boolean;
  roleId: string;
}

export class PermissionUpdate extends React.Component<IPermissionUpdateProps, IPermissionUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      roleId: '0',
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
  }

  saveEntity = (event, errors, values) => {
    values.createDate = convertDateTimeToServer(values.createDate);
    values.modifyDate = convertDateTimeToServer(values.modifyDate);

    if (errors.length === 0) {
      const { permissionEntity } = this.props;
      const entity = {
        ...permissionEntity,
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
    this.props.history.push('/entity/permission');
  };

  render() {
    const { permissionEntity, roles, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.permission.home.createOrEditLabel">
              <Translate contentKey="jhipsterSampleApplicationApp.permission.home.createOrEditLabel">Create or edit a Permission</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : permissionEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="permission-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="permission-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="permissionNameLabel" for="permission-permissionName">
                    <Translate contentKey="jhipsterSampleApplicationApp.permission.permissionName">Permission Name</Translate>
                  </Label>
                  <AvField
                    id="permission-permissionName"
                    type="text"
                    name="permissionName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="descriptionLabel" for="permission-description">
                    <Translate contentKey="jhipsterSampleApplicationApp.permission.description">Description</Translate>
                  </Label>
                  <AvField id="permission-description" type="text" name="description" />
                </AvGroup>
                <AvGroup>
                  <Label id="createDateLabel" for="permission-createDate">
                    <Translate contentKey="jhipsterSampleApplicationApp.permission.createDate">Create Date</Translate>
                  </Label>
                  <AvInput
                    id="permission-createDate"
                    type="datetime-local"
                    className="form-control"
                    name="createDate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.permissionEntity.createDate)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="modifyDateLabel" for="permission-modifyDate">
                    <Translate contentKey="jhipsterSampleApplicationApp.permission.modifyDate">Modify Date</Translate>
                  </Label>
                  <AvInput
                    id="permission-modifyDate"
                    type="datetime-local"
                    className="form-control"
                    name="modifyDate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.permissionEntity.modifyDate)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/permission" replace color="info">
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
  permissionEntity: storeState.permission.entity,
  loading: storeState.permission.loading,
  updating: storeState.permission.updating,
  updateSuccess: storeState.permission.updateSuccess
});

const mapDispatchToProps = {
  getRoles,
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
)(PermissionUpdate);
