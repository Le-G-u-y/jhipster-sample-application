import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './excercise-config.reducer';
import { IExcerciseConfig } from 'app/shared/model/excercise-config.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IExcerciseConfigProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class ExcerciseConfig extends React.Component<IExcerciseConfigProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { excerciseConfigList, match } = this.props;
    return (
      <div>
        <h2 id="excercise-config-heading">
          <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.home.title">Excercise Configs</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.home.createLabel">Create new Excercise Config</Translate>
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
                  <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.actualBpm">Actual Bpm</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.targetBpm">Target Bpm</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.minutes">Minutes</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.note">Note</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.createDate">Create Date</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.modifyDate">Modify Date</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.excercise">Excercise</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.plan">Plan</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {excerciseConfigList.map((excerciseConfig, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${excerciseConfig.id}`} color="link" size="sm">
                      {excerciseConfig.id}
                    </Button>
                  </td>
                  <td>{excerciseConfig.actualBpm}</td>
                  <td>{excerciseConfig.targetBpm}</td>
                  <td>{excerciseConfig.minutes}</td>
                  <td>{excerciseConfig.note}</td>
                  <td>
                    <TextFormat type="date" value={excerciseConfig.createDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={excerciseConfig.modifyDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    {excerciseConfig.excerciseId ? (
                      <Link to={`excercise/${excerciseConfig.excerciseId}`}>{excerciseConfig.excerciseId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{excerciseConfig.planId ? <Link to={`plan/${excerciseConfig.planId}`}>{excerciseConfig.planId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${excerciseConfig.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${excerciseConfig.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${excerciseConfig.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ excerciseConfig }: IRootState) => ({
  excerciseConfigList: excerciseConfig.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ExcerciseConfig);
