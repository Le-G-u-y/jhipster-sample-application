import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Permission from './permission';
import PermissionDetail from './permission-detail';
import PermissionUpdate from './permission-update';
import PermissionDeleteDialog from './permission-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PermissionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PermissionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PermissionDetail} />
      <ErrorBoundaryRoute path={match.url} component={Permission} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PermissionDeleteDialog} />
  </>
);

export default Routes;
