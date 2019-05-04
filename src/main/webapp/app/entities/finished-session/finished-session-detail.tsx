import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './finished-session.reducer';
import { IFinishedSession } from 'app/shared/model/finished-session.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFinishedSessionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class FinishedSessionDetail extends React.Component<IFinishedSessionDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { finishedSessionEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhipsterSampleApplicationApp.finishedSession.detail.title">FinishedSession</Translate> [
            <b>{finishedSessionEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="minutesTotal">
                <Translate contentKey="jhipsterSampleApplicationApp.finishedSession.minutesTotal">Minutes Total</Translate>
              </span>
            </dt>
            <dd>{finishedSessionEntity.minutesTotal}</dd>
            <dt>
              <span id="note">
                <Translate contentKey="jhipsterSampleApplicationApp.finishedSession.note">Note</Translate>
              </span>
            </dt>
            <dd>{finishedSessionEntity.note}</dd>
            <dt>
              <span id="createDate">
                <Translate contentKey="jhipsterSampleApplicationApp.finishedSession.createDate">Create Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={finishedSessionEntity.createDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="modifyDate">
                <Translate contentKey="jhipsterSampleApplicationApp.finishedSession.modifyDate">Modify Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={finishedSessionEntity.modifyDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <Translate contentKey="jhipsterSampleApplicationApp.finishedSession.plan">Plan</Translate>
            </dt>
            <dd>{finishedSessionEntity.planId ? finishedSessionEntity.planId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/finished-session" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/finished-session/${finishedSessionEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ finishedSession }: IRootState) => ({
  finishedSessionEntity: finishedSession.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FinishedSessionDetail);
