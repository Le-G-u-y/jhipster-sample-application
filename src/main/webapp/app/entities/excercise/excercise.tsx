import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './excercise.reducer';
import { IExcercise } from 'app/shared/model/excercise.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IExcerciseProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Excercise extends React.Component<IExcerciseProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { excerciseList, match } = this.props;
    return (
      <div>
        <h2 id="excercise-heading">
          <Translate contentKey="jhipsterSampleApplicationApp.excercise.home.title">Excercises</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhipsterSampleApplicationApp.excercise.home.createLabel">Create new Excercise</Translate>
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
                  <Translate contentKey="jhipsterSampleApplicationApp.excercise.excerciseName">Excercise Name</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.excercise.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.excercise.defaultMinutes">Default Minutes</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.excercise.defaultTargetBpm">Default Target Bpm</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.excercise.skillType">Skill Type</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.excercise.excerciseType">Excercise Type</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.excercise.createDate">Create Date</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.excercise.modifyDate">Modify Date</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.excercise.creator">Creator</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {excerciseList.map((excercise, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${excercise.id}`} color="link" size="sm">
                      {excercise.id}
                    </Button>
                  </td>
                  <td>{excercise.excerciseName}</td>
                  <td>{excercise.description}</td>
                  <td>{excercise.defaultMinutes}</td>
                  <td>{excercise.defaultTargetBpm}</td>
                  <td>
                    <Translate contentKey={`jhipsterSampleApplicationApp.SkillType.${excercise.skillType}`} />
                  </td>
                  <td>
                    <Translate contentKey={`jhipsterSampleApplicationApp.ExcerciseType.${excercise.excerciseType}`} />
                  </td>
                  <td>
                    <TextFormat type="date" value={excercise.createDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={excercise.modifyDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{excercise.creatorId ? <Link to={`drummer/${excercise.creatorId}`}>{excercise.creatorId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${excercise.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${excercise.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${excercise.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ excercise }: IRootState) => ({
  excerciseList: excercise.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Excercise);
