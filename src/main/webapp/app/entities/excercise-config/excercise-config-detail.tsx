import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './excercise-config.reducer';
import { IExcerciseConfig } from 'app/shared/model/excercise-config.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IExcerciseConfigDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ExcerciseConfigDetail extends React.Component<IExcerciseConfigDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { excerciseConfigEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.detail.title">ExcerciseConfig</Translate> [
            <b>{excerciseConfigEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="actualBpm">
                <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.actualBpm">Actual Bpm</Translate>
              </span>
            </dt>
            <dd>{excerciseConfigEntity.actualBpm}</dd>
            <dt>
              <span id="targetBpm">
                <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.targetBpm">Target Bpm</Translate>
              </span>
            </dt>
            <dd>{excerciseConfigEntity.targetBpm}</dd>
            <dt>
              <span id="minutes">
                <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.minutes">Minutes</Translate>
              </span>
            </dt>
            <dd>{excerciseConfigEntity.minutes}</dd>
            <dt>
              <span id="note">
                <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.note">Note</Translate>
              </span>
            </dt>
            <dd>{excerciseConfigEntity.note}</dd>
            <dt>
              <span id="createDate">
                <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.createDate">Create Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={excerciseConfigEntity.createDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="modifyDate">
                <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.modifyDate">Modify Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={excerciseConfigEntity.modifyDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.excercise">Excercise</Translate>
            </dt>
            <dd>{excerciseConfigEntity.excerciseId ? excerciseConfigEntity.excerciseId : ''}</dd>
            <dt>
              <Translate contentKey="jhipsterSampleApplicationApp.excerciseConfig.plan">Plan</Translate>
            </dt>
            <dd>{excerciseConfigEntity.planId ? excerciseConfigEntity.planId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/excercise-config" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/excercise-config/${excerciseConfigEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ excerciseConfig }: IRootState) => ({
  excerciseConfigEntity: excerciseConfig.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ExcerciseConfigDetail);
