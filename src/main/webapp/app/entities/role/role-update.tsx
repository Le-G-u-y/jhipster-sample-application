import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IPermission } from 'app/shared/model/permission.model';
import { getEntities as getPermissions } from 'app/entities/permission/permission.reducer';
import { getEntity, updateEntity, createEntity, reset } from './role.reducer';
import { IRole } from 'app/shared/model/role.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IRoleUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IRoleUpdateState {
  isNew: boolean;
  idspermission: any[];
}

export class RoleUpdate extends React.Component<IRoleUpdateProps, IRoleUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idspermission: [],
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

    this.props.getPermissions();
  }

  saveEntity = (event, errors, values) => {
    values.createDate = convertDateTimeToServer(values.createDate);
    values.modifyDate = convertDateTimeToServer(values.modifyDate);

    if (errors.length === 0) {
      const { roleEntity } = this.props;
      const entity = {
        ...roleEntity,
        ...values,
        permissions: mapIdList(values.permissions)
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/role');
  };

  render() {
    const { roleEntity, permissions, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.role.home.createOrEditLabel">
              <Translate contentKey="jhipsterSampleApplicationApp.role.home.createOrEditLabel">Create or edit a Role</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : roleEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="role-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="role-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="roleNameLabel" for="role-roleName">
                    <Translate contentKey="jhipsterSampleApplicationApp.role.roleName">Role Name</Translate>
                  </Label>
                  <AvField
                    id="role-roleName"
                    type="text"
                    name="roleName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="descriptionLabel" for="role-description">
                    <Translate contentKey="jhipsterSampleApplicationApp.role.description">Description</Translate>
                  </Label>
                  <AvField id="role-description" type="text" name="description" />
                </AvGroup>
                <AvGroup>
                  <Label id="createDateLabel" for="role-createDate">
                    <Translate contentKey="jhipsterSampleApplicationApp.role.createDate">Create Date</Translate>
                  </Label>
                  <AvInput
                    id="role-createDate"
                    type="datetime-local"
                    className="form-control"
                    name="createDate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.roleEntity.createDate)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="modifyDateLabel" for="role-modifyDate">
                    <Translate contentKey="jhipsterSampleApplicationApp.role.modifyDate">Modify Date</Translate>
                  </Label>
                  <AvInput
                    id="role-modifyDate"
                    type="datetime-local"
                    className="form-control"
                    name="modifyDate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.roleEntity.modifyDate)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="role-permission">
                    <Translate contentKey="jhipsterSampleApplicationApp.role.permission">Permission</Translate>
                  </Label>
                  <AvInput
                    id="role-permission"
                    type="select"
                    multiple
                    className="form-control"
                    name="permissions"
                    value={roleEntity.permissions && roleEntity.permissions.map(e => e.id)}
                  >
                    <option value="" key="0" />
                    {permissions
                      ? permissions.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/role" replace color="info">
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
  permissions: storeState.permission.entities,
  roleEntity: storeState.role.entity,
  loading: storeState.role.loading,
  updating: storeState.role.updating,
  updateSuccess: storeState.role.updateSuccess
});

const mapDispatchToProps = {
  getPermissions,
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
)(RoleUpdate);
