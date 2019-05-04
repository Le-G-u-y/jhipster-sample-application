import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Excercise from './excercise';
import ExcerciseDetail from './excercise-detail';
import ExcerciseUpdate from './excercise-update';
import ExcerciseDeleteDialog from './excercise-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ExcerciseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ExcerciseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ExcerciseDetail} />
      <ErrorBoundaryRoute path={match.url} component={Excercise} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ExcerciseDeleteDialog} />
  </>
);

export default Routes;
