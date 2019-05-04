import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './i-18-n-text.reducer';
import { II18nText } from 'app/shared/model/i-18-n-text.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface II18nTextProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class I18nText extends React.Component<II18nTextProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { i18nTextList, match } = this.props;
    return (
      <div>
        <h2 id="i-18-n-text-heading">
          <Translate contentKey="jhipsterSampleApplicationApp.i18nText.home.title">I 18 N Texts</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhipsterSampleApplicationApp.i18nText.home.createLabel">Create new I 18 N Text</Translate>
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
                  <Translate contentKey="jhipsterSampleApplicationApp.i18nText.locale">Locale</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.i18nText.textKey">Text Key</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.i18nText.textContent">Text Content</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.i18nText.createDate">Create Date</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.i18nText.modifyDate">Modify Date</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {i18nTextList.map((i18nText, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${i18nText.id}`} color="link" size="sm">
                      {i18nText.id}
                    </Button>
                  </td>
                  <td>{i18nText.locale}</td>
                  <td>{i18nText.textKey}</td>
                  <td>{i18nText.textContent}</td>
                  <td>
                    <TextFormat type="date" value={i18nText.createDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={i18nText.modifyDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${i18nText.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${i18nText.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${i18nText.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ i18nText }: IRootState) => ({
  i18nTextList: i18nText.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(I18nText);
