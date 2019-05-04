import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './drummer-statistics.reducer';
import { IDrummerStatistics } from 'app/shared/model/drummer-statistics.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDrummerStatisticsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class DrummerStatisticsDetail extends React.Component<IDrummerStatisticsDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { drummerStatisticsEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.detail.title">DrummerStatistics</Translate> [
            <b>{drummerStatisticsEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="selfAssessedLevelSpeed">
                <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.selfAssessedLevelSpeed">
                  Self Assessed Level Speed
                </Translate>
              </span>
            </dt>
            <dd>{drummerStatisticsEntity.selfAssessedLevelSpeed}</dd>
            <dt>
              <span id="selfAssessedLevelGroove">
                <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.selfAssessedLevelGroove">
                  Self Assessed Level Groove
                </Translate>
              </span>
            </dt>
            <dd>{drummerStatisticsEntity.selfAssessedLevelGroove}</dd>
            <dt>
              <span id="selfAssessedLevelCreativity">
                <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.selfAssessedLevelCreativity">
                  Self Assessed Level Creativity
                </Translate>
              </span>
            </dt>
            <dd>{drummerStatisticsEntity.selfAssessedLevelCreativity}</dd>
            <dt>
              <span id="selfAssessedLevelAdaptability">
                <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.selfAssessedLevelAdaptability">
                  Self Assessed Level Adaptability
                </Translate>
              </span>
            </dt>
            <dd>{drummerStatisticsEntity.selfAssessedLevelAdaptability}</dd>
            <dt>
              <span id="selfAssessedLevelDynamics">
                <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.selfAssessedLevelDynamics">
                  Self Assessed Level Dynamics
                </Translate>
              </span>
            </dt>
            <dd>{drummerStatisticsEntity.selfAssessedLevelDynamics}</dd>
            <dt>
              <span id="selfAssessedLevelIndependence">
                <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.selfAssessedLevelIndependence">
                  Self Assessed Level Independence
                </Translate>
              </span>
            </dt>
            <dd>{drummerStatisticsEntity.selfAssessedLevelIndependence}</dd>
            <dt>
              <span id="selfAssessedLevelLivePerformance">
                <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.selfAssessedLevelLivePerformance">
                  Self Assessed Level Live Performance
                </Translate>
              </span>
            </dt>
            <dd>{drummerStatisticsEntity.selfAssessedLevelLivePerformance}</dd>
            <dt>
              <span id="selfAssessedLevelReadingMusic">
                <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.selfAssessedLevelReadingMusic">
                  Self Assessed Level Reading Music
                </Translate>
              </span>
            </dt>
            <dd>{drummerStatisticsEntity.selfAssessedLevelReadingMusic}</dd>
            <dt>
              <span id="createDate">
                <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.createDate">Create Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={drummerStatisticsEntity.createDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="modifyDate">
                <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.modifyDate">Modify Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={drummerStatisticsEntity.modifyDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <Translate contentKey="jhipsterSampleApplicationApp.drummerStatistics.drummer">Drummer</Translate>
            </dt>
            <dd>{drummerStatisticsEntity.drummerId ? drummerStatisticsEntity.drummerId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/drummer-statistics" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/drummer-statistics/${drummerStatisticsEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ drummerStatistics }: IRootState) => ({
  drummerStatisticsEntity: drummerStatistics.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DrummerStatisticsDetail);
