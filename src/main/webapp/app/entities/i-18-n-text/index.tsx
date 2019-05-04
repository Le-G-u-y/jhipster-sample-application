import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import I18nText from './i-18-n-text';
import I18nTextDetail from './i-18-n-text-detail';
import I18nTextUpdate from './i-18-n-text-update';
import I18nTextDeleteDialog from './i-18-n-text-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={I18nTextUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={I18nTextUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={I18nTextDetail} />
      <ErrorBoundaryRoute path={match.url} component={I18nText} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={I18nTextDeleteDialog} />
  </>
);

export default Routes;
