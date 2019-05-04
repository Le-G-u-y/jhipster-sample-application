import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FinishedSession from './finished-session';
import FinishedSessionDetail from './finished-session-detail';
import FinishedSessionUpdate from './finished-session-update';
import FinishedSessionDeleteDialog from './finished-session-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FinishedSessionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FinishedSessionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FinishedSessionDetail} />
      <ErrorBoundaryRoute path={match.url} component={FinishedSession} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={FinishedSessionDeleteDialog} />
  </>
);

export default Routes;
