import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './permission.reducer';
import { IPermission } from 'app/shared/model/permission.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPermissionProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Permission extends React.Component<IPermissionProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { permissionList, match } = this.props;
    return (
      <div>
        <h2 id="permission-heading">
          <Translate contentKey="jhipsterSampleApplicationApp.permission.home.title">Permissions</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhipsterSampleApplicationApp.permission.home.createLabel">Create new Permission</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.permission.permissionName">Permission Name</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.permission.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.permission.createDate">Create Date</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.permission.modifyDate">Modify Date</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {permissionList.map((permission, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${permission.id}`} color="link" size="sm">
                      {permission.id}
                    </Button>
                  </td>
                  <td>{permission.permissionName}</td>
                  <td>{permission.description}</td>
                  <td>
                    <TextFormat type="date" value={permission.createDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={permission.modifyDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${permission.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${permission.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${permission.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ permission }: IRootState) => ({
  permissionList: permission.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Permission);
