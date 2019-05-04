import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './drummer.reducer';
import { IDrummer } from 'app/shared/model/drummer.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDrummerProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Drummer extends React.Component<IDrummerProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { drummerList, match } = this.props;
    return (
      <div>
        <h2 id="drummer-heading">
          <Translate contentKey="jhipsterSampleApplicationApp.drummer.home.title">Drummers</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhipsterSampleApplicationApp.drummer.home.createLabel">Create new Drummer</Translate>
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
                  <Translate contentKey="jhipsterSampleApplicationApp.drummer.drummerName">Drummer Name</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.drummer.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.drummer.email">Email</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.drummer.createDate">Create Date</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.drummer.modifyDate">Modify Date</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.drummer.role">Role</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {drummerList.map((drummer, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${drummer.id}`} color="link" size="sm">
                      {drummer.id}
                    </Button>
                  </td>
                  <td>{drummer.drummerName}</td>
                  <td>{drummer.description}</td>
                  <td>{drummer.email}</td>
                  <td>
                    <TextFormat type="date" value={drummer.createDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={drummer.modifyDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{drummer.roleId ? <Link to={`role/${drummer.roleId}`}>{drummer.roleId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${drummer.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${drummer.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${drummer.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ drummer }: IRootState) => ({
  drummerList: drummer.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Drummer);
