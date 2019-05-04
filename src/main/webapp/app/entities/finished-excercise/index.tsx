import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FinishedExcercise from './finished-excercise';
import FinishedExcerciseDetail from './finished-excercise-detail';
import FinishedExcerciseUpdate from './finished-excercise-update';
import FinishedExcerciseDeleteDialog from './finished-excercise-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FinishedExcerciseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FinishedExcerciseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FinishedExcerciseDetail} />
      <ErrorBoundaryRoute path={match.url} component={FinishedExcercise} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={FinishedExcerciseDeleteDialog} />
  </>
);

export default Routes;
