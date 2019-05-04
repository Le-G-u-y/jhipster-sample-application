import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './excercise.reducer';
import { IExcercise } from 'app/shared/model/excercise.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IExcerciseDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ExcerciseDetail extends React.Component<IExcerciseDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { excerciseEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhipsterSampleApplicationApp.excercise.detail.title">Excercise</Translate> [<b>{excerciseEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="excerciseName">
                <Translate contentKey="jhipsterSampleApplicationApp.excercise.excerciseName">Excercise Name</Translate>
              </span>
            </dt>
            <dd>{excerciseEntity.excerciseName}</dd>
            <dt>
              <span id="description">
                <Translate contentKey="jhipsterSampleApplicationApp.excercise.description">Description</Translate>
              </span>
            </dt>
            <dd>{excerciseEntity.description}</dd>
            <dt>
              <span id="defaultMinutes">
                <Translate contentKey="jhipsterSampleApplicationApp.excercise.defaultMinutes">Default Minutes</Translate>
              </span>
            </dt>
            <dd>{excerciseEntity.defaultMinutes}</dd>
            <dt>
              <span id="defaultTargetBpm">
                <Translate contentKey="jhipsterSampleApplicationApp.excercise.defaultTargetBpm">Default Target Bpm</Translate>
              </span>
            </dt>
            <dd>{excerciseEntity.defaultTargetBpm}</dd>
            <dt>
              <span id="skillType">
                <Translate contentKey="jhipsterSampleApplicationApp.excercise.skillType">Skill Type</Translate>
              </span>
            </dt>
            <dd>{excerciseEntity.skillType}</dd>
            <dt>
              <span id="excerciseType">
                <Translate contentKey="jhipsterSampleApplicationApp.excercise.excerciseType">Excercise Type</Translate>
              </span>
            </dt>
            <dd>{excerciseEntity.excerciseType}</dd>
            <dt>
              <span id="createDate">
                <Translate contentKey="jhipsterSampleApplicationApp.excercise.createDate">Create Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={excerciseEntity.createDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="modifyDate">
                <Translate contentKey="jhipsterSampleApplicationApp.excercise.modifyDate">Modify Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={excerciseEntity.modifyDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <Translate contentKey="jhipsterSampleApplicationApp.excercise.creator">Creator</Translate>
            </dt>
            <dd>{excerciseEntity.creatorId ? excerciseEntity.creatorId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/excercise" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/excercise/${excerciseEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ excercise }: IRootState) => ({
  excerciseEntity: excercise.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ExcerciseDetail);
