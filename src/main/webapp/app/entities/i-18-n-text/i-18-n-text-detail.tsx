import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './i-18-n-text.reducer';
import { II18nText } from 'app/shared/model/i-18-n-text.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface II18nTextDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class I18nTextDetail extends React.Component<II18nTextDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { i18nTextEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhipsterSampleApplicationApp.i18nText.detail.title">I18nText</Translate> [<b>{i18nTextEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="locale">
                <Translate contentKey="jhipsterSampleApplicationApp.i18nText.locale">Locale</Translate>
              </span>
            </dt>
            <dd>{i18nTextEntity.locale}</dd>
            <dt>
              <span id="textKey">
                <Translate contentKey="jhipsterSampleApplicationApp.i18nText.textKey">Text Key</Translate>
              </span>
            </dt>
            <dd>{i18nTextEntity.textKey}</dd>
            <dt>
              <span id="textContent">
                <Translate contentKey="jhipsterSampleApplicationApp.i18nText.textContent">Text Content</Translate>
              </span>
            </dt>
            <dd>{i18nTextEntity.textContent}</dd>
            <dt>
              <span id="createDate">
                <Translate contentKey="jhipsterSampleApplicationApp.i18nText.createDate">Create Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={i18nTextEntity.createDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="modifyDate">
                <Translate contentKey="jhipsterSampleApplicationApp.i18nText.modifyDate">Modify Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={i18nTextEntity.modifyDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
          </dl>
          <Button tag={Link} to="/entity/i-18-n-text" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/i-18-n-text/${i18nTextEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ i18nText }: IRootState) => ({
  i18nTextEntity: i18nText.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(I18nTextDetail);
