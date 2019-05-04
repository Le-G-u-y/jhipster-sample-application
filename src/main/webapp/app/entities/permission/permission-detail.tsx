import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './permission.reducer';
import { IPermission } from 'app/shared/model/permission.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPermissionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PermissionDetail extends React.Component<IPermissionDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { permissionEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhipsterSampleApplicationApp.permission.detail.title">Permission</Translate> [
            <b>{permissionEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="permissionName">
                <Translate contentKey="jhipsterSampleApplicationApp.permission.permissionName">Permission Name</Translate>
              </span>
            </dt>
            <dd>{permissionEntity.permissionName}</dd>
            <dt>
              <span id="description">
                <Translate contentKey="jhipsterSampleApplicationApp.permission.description">Description</Translate>
              </span>
            </dt>
            <dd>{permissionEntity.description}</dd>
            <dt>
              <span id="createDate">
                <Translate contentKey="jhipsterSampleApplicationApp.permission.createDate">Create Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={permissionEntity.createDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="modifyDate">
                <Translate contentKey="jhipsterSampleApplicationApp.permission.modifyDate">Modify Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={permissionEntity.modifyDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
          </dl>
          <Button tag={Link} to="/entity/permission" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/permission/${permissionEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ permission }: IRootState) => ({
  permissionEntity: permission.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PermissionDetail);
