import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Drummer from './drummer';
import DrummerDetail from './drummer-detail';
import DrummerUpdate from './drummer-update';
import DrummerDeleteDialog from './drummer-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DrummerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DrummerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DrummerDetail} />
      <ErrorBoundaryRoute path={match.url} component={Drummer} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={DrummerDeleteDialog} />
  </>
);

export default Routes;
