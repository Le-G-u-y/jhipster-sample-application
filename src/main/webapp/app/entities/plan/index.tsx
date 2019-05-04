import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Plan from './plan';
import PlanDetail from './plan-detail';
import PlanUpdate from './plan-update';
import PlanDeleteDialog from './plan-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PlanUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PlanUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PlanDetail} />
      <ErrorBoundaryRoute path={match.url} component={Plan} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PlanDeleteDialog} />
  </>
);

export default Routes;
