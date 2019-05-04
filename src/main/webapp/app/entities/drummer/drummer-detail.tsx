import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './drummer.reducer';
import { IDrummer } from 'app/shared/model/drummer.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDrummerDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class DrummerDetail extends React.Component<IDrummerDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { drummerEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhipsterSampleApplicationApp.drummer.detail.title">Drummer</Translate> [<b>{drummerEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="drummerName">
                <Translate contentKey="jhipsterSampleApplicationApp.drummer.drummerName">Drummer Name</Translate>
              </span>
            </dt>
            <dd>{drummerEntity.drummerName}</dd>
            <dt>
              <span id="description">
                <Translate contentKey="jhipsterSampleApplicationApp.drummer.description">Description</Translate>
              </span>
            </dt>
            <dd>{drummerEntity.description}</dd>
            <dt>
              <span id="email">
                <Translate contentKey="jhipsterSampleApplicationApp.drummer.email">Email</Translate>
              </span>
            </dt>
            <dd>{drummerEntity.email}</dd>
            <dt>
              <span id="createDate">
                <Translate contentKey="jhipsterSampleApplicationApp.drummer.createDate">Create Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={drummerEntity.createDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="modifyDate">
                <Translate contentKey="jhipsterSampleApplicationApp.drummer.modifyDate">Modify Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={drummerEntity.modifyDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <Translate contentKey="jhipsterSampleApplicationApp.drummer.role">Role</Translate>
            </dt>
            <dd>{drummerEntity.roleId ? drummerEntity.roleId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/drummer" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/drummer/${drummerEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ drummer }: IRootState) => ({
  drummerEntity: drummer.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DrummerDetail);
