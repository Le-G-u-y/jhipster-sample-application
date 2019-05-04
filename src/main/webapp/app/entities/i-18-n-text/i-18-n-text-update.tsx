import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './i-18-n-text.reducer';
import { II18nText } from 'app/shared/model/i-18-n-text.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface II18nTextUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface II18nTextUpdateState {
  isNew: boolean;
}

export class I18nTextUpdate extends React.Component<II18nTextUpdateProps, II18nTextUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    values.createDate = convertDateTimeToServer(values.createDate);
    values.modifyDate = convertDateTimeToServer(values.modifyDate);

    if (errors.length === 0) {
      const { i18nTextEntity } = this.props;
      const entity = {
        ...i18nTextEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/i-18-n-text');
  };

  render() {
    const { i18nTextEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.i18nText.home.createOrEditLabel">
              <Translate contentKey="jhipsterSampleApplicationApp.i18nText.home.createOrEditLabel">Create or edit a I18nText</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : i18nTextEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="i-18-n-text-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="i-18-n-text-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="localeLabel" for="i-18-n-text-locale">
                    <Translate contentKey="jhipsterSampleApplicationApp.i18nText.locale">Locale</Translate>
                  </Label>
                  <AvField
                    id="i-18-n-text-locale"
                    type="text"
                    name="locale"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="textKeyLabel" for="i-18-n-text-textKey">
                    <Translate contentKey="jhipsterSampleApplicationApp.i18nText.textKey">Text Key</Translate>
                  </Label>
                  <AvField
                    id="i-18-n-text-textKey"
                    type="text"
                    name="textKey"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="textContentLabel" for="i-18-n-text-textContent">
                    <Translate contentKey="jhipsterSampleApplicationApp.i18nText.textContent">Text Content</Translate>
                  </Label>
                  <AvField id="i-18-n-text-textContent" type="text" name="textContent" />
                </AvGroup>
                <AvGroup>
                  <Label id="createDateLabel" for="i-18-n-text-createDate">
                    <Translate contentKey="jhipsterSampleApplicationApp.i18nText.createDate">Create Date</Translate>
                  </Label>
                  <AvInput
                    id="i-18-n-text-createDate"
                    type="datetime-local"
                    className="form-control"
                    name="createDate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.i18nTextEntity.createDate)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="modifyDateLabel" for="i-18-n-text-modifyDate">
                    <Translate contentKey="jhipsterSampleApplicationApp.i18nText.modifyDate">Modify Date</Translate>
                  </Label>
                  <AvInput
                    id="i-18-n-text-modifyDate"
                    type="datetime-local"
                    className="form-control"
                    name="modifyDate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.i18nTextEntity.modifyDate)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/i-18-n-text" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  i18nTextEntity: storeState.i18nText.entity,
  loading: storeState.i18nText.loading,
  updating: storeState.i18nText.updating,
  updateSuccess: storeState.i18nText.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(I18nTextUpdate);
