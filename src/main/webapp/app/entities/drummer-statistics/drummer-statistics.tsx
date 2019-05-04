import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './drummer-statistics.reducer';
import { IDrummerStatistics } from 'app/shared/model/drummer-statistics.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDrummerStatisticsProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class DrummerStatistics extends React.Component<IDrummerStatisticsProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { drummerStatisticsList, match } = this.props;
    return (
      <div>
        <h2 id="drummer-statistics-heading">
          <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.home.title">Drummer Statistics</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.home.createLabel">
              Create new Drummer Statistics
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
                  <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.selfAssessedLevelSpeed">
                    Self Assessed Level Speed
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.selfAssessedLevelGroove">
                    Self Assessed Level Groove
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.selfAssessedLevelCreativity">
                    Self Assessed Level Creativity
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.selfAssessedLevelAdaptability">
                    Self Assessed Level Adaptability
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.selfAssessedLevelDynamics">
                    Self Assessed Level Dynamics
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.selfAssessedLevelIndependence">
                    Self Assessed Level Independence
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.selfAssessedLevelLivePerformance">
                    Self Assessed Level Live Performance
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.selfAssessedLevelReadingMusic">
                    Self Assessed Level Reading Music
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.createDate">Create Date</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.modifyDate">Modify Date</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.drummer">Drummer</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {drummerStatisticsList.map((drummerStatistics, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${drummerStatistics.id}`} color="link" size="sm">
                      {drummerStatistics.id}
                    </Button>
                  </td>
                  <td>{drummerStatistics.selfAssessedLevelSpeed}</td>
                  <td>{drummerStatistics.selfAssessedLevelGroove}</td>
                  <td>{drummerStatistics.selfAssessedLevelCreativity}</td>
                  <td>{drummerStatistics.selfAssessedLevelAdaptability}</td>
                  <td>{drummerStatistics.selfAssessedLevelDynamics}</td>
                  <td>{drummerStatistics.selfAssessedLevelIndependence}</td>
                  <td>{drummerStatistics.selfAssessedLevelLivePerformance}</td>
                  <td>{drummerStatistics.selfAssessedLevelReadingMusic}</td>
                  <td>
                    <TextFormat type="date" value={drummerStatistics.createDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={drummerStatistics.modifyDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    {drummerStatistics.drummerId ? (
                      <Link to={`drummer/${drummerStatistics.drummerId}`}>{drummerStatistics.drummerId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${drummerStatistics.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${drummerStatistics.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${drummerStatistics.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ drummerStatistics }: IRootState) => ({
  drummerStatisticsList: drummerStatistics.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DrummerStatistics);
