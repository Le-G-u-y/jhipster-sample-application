import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './finished-excercise.reducer';
import { IFinishedExcercise } from 'app/shared/model/finished-excercise.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFinishedExcerciseProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class FinishedExcercise extends React.Component<IFinishedExcerciseProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { finishedExcerciseList, match } = this.props;
    return (
      <div>
        <h2 id="finished-excercise-heading">
          <Translate contentKey="jhipsterSampleApplicationApp.finishedExcercise.home.title">Finished Excercises</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhipsterSampleApplicationApp.finishedExcercise.home.createLabel">
              Create new Finished Excercise
            </Translate>
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
                  <Translate contentKey="jhipsterSampleApplicationApp.finishedExcercise.actualBpm">Actual Bpm</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.finishedExcercise.actualMinutes">Actual Minutes</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.finishedExcercise.createDate">Create Date</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.finishedExcercise.modifyDate">Modify Date</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.finishedExcercise.excercise">Excercise</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.finishedExcercise.finishedSession">Finished Session</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {finishedExcerciseList.map((finishedExcercise, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${finishedExcercise.id}`} color="link" size="sm">
                      {finishedExcercise.id}
                    </Button>
                  </td>
                  <td>{finishedExcercise.actualBpm}</td>
                  <td>{finishedExcercise.actualMinutes}</td>
                  <td>
                    <TextFormat type="date" value={finishedExcercise.createDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={finishedExcercise.modifyDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    {finishedExcercise.excerciseId ? (
                      <Link to={`excercise/${finishedExcercise.excerciseId}`}>{finishedExcercise.excerciseId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {finishedExcercise.finishedSessionId ? (
                      <Link to={`finished-session/${finishedExcercise.finishedSessionId}`}>{finishedExcercise.finishedSessionId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${finishedExcercise.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${finishedExcercise.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${finishedExcercise.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ finishedExcercise }: IRootState) => ({
  finishedExcerciseList: finishedExcercise.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FinishedExcercise);
