import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './plan.reducer';
import { IPlan } from 'app/shared/model/plan.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPlanProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Plan extends React.Component<IPlanProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { planList, match } = this.props;
    return (
      <div>
        <h2 id="plan-heading">
          <Translate contentKey="jhipsterSampleApplicationApp.plan.home.title">Plans</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhipsterSampleApplicationApp.plan.home.createLabel">Create new Plan</Translate>
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
                  <Translate contentKey="jhipsterSampleApplicationApp.plan.planName">Plan Name</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.plan.planFocus">Plan Focus</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.plan.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.plan.minutesPerSession">Minutes Per Session</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.plan.sessionsPerWeek">Sessions Per Week</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.plan.targetDate">Target Date</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.plan.createDate">Create Date</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.plan.modifyDate">Modify Date</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.plan.creator">Creator</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.plan.owner">Owner</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {planList.map((plan, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${plan.id}`} color="link" size="sm">
                      {plan.id}
                    </Button>
                  </td>
                  <td>{plan.planName}</td>
                  <td>{plan.planFocus}</td>
                  <td>{plan.description}</td>
                  <td>{plan.minutesPerSession}</td>
                  <td>{plan.sessionsPerWeek}</td>
                  <td>
                    <TextFormat type="date" value={plan.targetDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={plan.createDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={plan.modifyDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{plan.creatorId ? <Link to={`drummer/${plan.creatorId}`}>{plan.creatorId}</Link> : ''}</td>
                  <td>{plan.ownerId ? <Link to={`drummer/${plan.ownerId}`}>{plan.ownerId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${plan.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${plan.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${plan.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ plan }: IRootState) => ({
  planList: plan.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Plan);
