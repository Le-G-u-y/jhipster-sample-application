import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './finished-excercise.reducer';
import { IFinishedExcercise } from 'app/shared/model/finished-excercise.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFinishedExcerciseDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class FinishedExcerciseDetail extends React.Component<IFinishedExcerciseDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { finishedExcerciseEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhipsterSampleApplicationApp.finishedExcercise.detail.title">FinishedExcercise</Translate> [
            <b>{finishedExcerciseEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="actualBpm">
                <Translate contentKey="jhipsterSampleApplicationApp.finishedExcercise.actualBpm">Actual Bpm</Translate>
              </span>
            </dt>
            <dd>{finishedExcerciseEntity.actualBpm}</dd>
            <dt>
              <span id="actualMinutes">
                <Translate contentKey="jhipsterSampleApplicationApp.finishedExcercise.actualMinutes">Actual Minutes</Translate>
              </span>
            </dt>
            <dd>{finishedExcerciseEntity.actualMinutes}</dd>
            <dt>
              <span id="createDate">
                <Translate contentKey="jhipsterSampleApplicationApp.finishedExcercise.createDate">Create Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={finishedExcerciseEntity.createDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="modifyDate">
                <Translate contentKey="jhipsterSampleApplicationApp.finishedExcercise.modifyDate">Modify Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={finishedExcerciseEntity.modifyDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <Translate contentKey="jhipsterSampleApplicationApp.finishedExcercise.excercise">Excercise</Translate>
            </dt>
            <dd>{finishedExcerciseEntity.excerciseId ? finishedExcerciseEntity.excerciseId : ''}</dd>
            <dt>
              <Translate contentKey="jhipsterSampleApplicationApp.finishedExcercise.finishedSession">Finished Session</Translate>
            </dt>
            <dd>{finishedExcerciseEntity.finishedSessionId ? finishedExcerciseEntity.finishedSessionId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/finished-excercise" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/finished-excercise/${finishedExcerciseEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ finishedExcercise }: IRootState) => ({
  finishedExcerciseEntity: finishedExcercise.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FinishedExcerciseDetail);
