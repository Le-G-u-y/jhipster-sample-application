import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './plan.reducer';
import { IPlan } from 'app/shared/model/plan.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPlanDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PlanDetail extends React.Component<IPlanDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { planEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhipsterSampleApplicationApp.plan.detail.title">Plan</Translate> [<b>{planEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="planName">
                <Translate contentKey="jhipsterSampleApplicationApp.plan.planName">Plan Name</Translate>
              </span>
            </dt>
            <dd>{planEntity.planName}</dd>
            <dt>
              <span id="planFocus">
                <Translate contentKey="jhipsterSampleApplicationApp.plan.planFocus">Plan Focus</Translate>
              </span>
            </dt>
            <dd>{planEntity.planFocus}</dd>
            <dt>
              <span id="description">
                <Translate contentKey="jhipsterSampleApplicationApp.plan.description">Description</Translate>
              </span>
            </dt>
            <dd>{planEntity.description}</dd>
            <dt>
              <span id="minutesPerSession">
                <Translate contentKey="jhipsterSampleApplicationApp.plan.minutesPerSession">Minutes Per Session</Translate>
              </span>
            </dt>
            <dd>{planEntity.minutesPerSession}</dd>
            <dt>
              <span id="sessionsPerWeek">
                <Translate contentKey="jhipsterSampleApplicationApp.plan.sessionsPerWeek">Sessions Per Week</Translate>
              </span>
            </dt>
            <dd>{planEntity.sessionsPerWeek}</dd>
            <dt>
              <span id="targetDate">
                <Translate contentKey="jhipsterSampleApplicationApp.plan.targetDate">Target Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={planEntity.targetDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="createDate">
                <Translate contentKey="jhipsterSampleApplicationApp.plan.createDate">Create Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={planEntity.createDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="modifyDate">
                <Translate contentKey="jhipsterSampleApplicationApp.plan.modifyDate">Modify Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={planEntity.modifyDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <Translate contentKey="jhipsterSampleApplicationApp.plan.creator">Creator</Translate>
            </dt>
            <dd>{planEntity.creatorId ? planEntity.creatorId : ''}</dd>
            <dt>
              <Translate contentKey="jhipsterSampleApplicationApp.plan.owner">Owner</Translate>
            </dt>
            <dd>{planEntity.ownerId ? planEntity.ownerId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/plan" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/plan/${planEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ plan }: IRootState) => ({
  planEntity: plan.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PlanDetail);
