import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ExcerciseConfig from './excercise-config';
import ExcerciseConfigDetail from './excercise-config-detail';
import ExcerciseConfigUpdate from './excercise-config-update';
import ExcerciseConfigDeleteDialog from './excercise-config-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ExcerciseConfigUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ExcerciseConfigUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ExcerciseConfigDetail} />
      <ErrorBoundaryRoute path={match.url} component={ExcerciseConfig} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ExcerciseConfigDeleteDialog} />
  </>
);

export default Routes;
