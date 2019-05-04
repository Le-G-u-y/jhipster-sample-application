import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './role.reducer';
import { IRole } from 'app/shared/model/role.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IRoleDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class RoleDetail extends React.Component<IRoleDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { roleEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhipsterSampleApplicationApp.role.detail.title">Role</Translate> [<b>{roleEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="roleName">
                <Translate contentKey="jhipsterSampleApplicationApp.role.roleName">Role Name</Translate>
              </span>
            </dt>
            <dd>{roleEntity.roleName}</dd>
            <dt>
              <span id="description">
                <Translate contentKey="jhipsterSampleApplicationApp.role.description">Description</Translate>
              </span>
            </dt>
            <dd>{roleEntity.description}</dd>
            <dt>
              <span id="createDate">
                <Translate contentKey="jhipsterSampleApplicationApp.role.createDate">Create Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={roleEntity.createDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="modifyDate">
                <Translate contentKey="jhipsterSampleApplicationApp.role.modifyDate">Modify Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={roleEntity.modifyDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <Translate contentKey="jhipsterSampleApplicationApp.role.permission">Permission</Translate>
            </dt>
            <dd>
              {roleEntity.permissions
                ? roleEntity.permissions.map((val, i) => (
                    <span key={val.id}>
                      <a>{val.id}</a>
                      {i === roleEntity.permissions.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}
            </dd>
          </dl>
          <Button tag={Link} to="/entity/role" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/role/${roleEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ role }: IRootState) => ({
  roleEntity: role.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(RoleDetail);
