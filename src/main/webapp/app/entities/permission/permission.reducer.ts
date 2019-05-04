import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPermission, defaultValue } from 'app/shared/model/permission.model';

export const ACTION_TYPES = {
  FETCH_PERMISSION_LIST: 'permission/FETCH_PERMISSION_LIST',
  FETCH_PERMISSION: 'permission/FETCH_PERMISSION',
  CREATE_PERMISSION: 'permission/CREATE_PERMISSION',
  UPDATE_PERMISSION: 'permission/UPDATE_PERMISSION',
  DELETE_PERMISSION: 'permission/DELETE_PERMISSION',
  RESET: 'permission/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPermission>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PermissionState = Readonly<typeof initialState>;

// Reducer

export default (state: PermissionState = initialState, action): PermissionState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PERMISSION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PERMISSION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PERMISSION):
    case REQUEST(ACTION_TYPES.UPDATE_PERMISSION):
    case REQUEST(ACTION_TYPES.DELETE_PERMISSION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PERMISSION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PERMISSION):
    case FAILURE(ACTION_TYPES.CREATE_PERMISSION):
    case FAILURE(ACTION_TYPES.UPDATE_PERMISSION):
    case FAILURE(ACTION_TYPES.DELETE_PERMISSION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PERMISSION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PERMISSION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PERMISSION):
    case SUCCESS(ACTION_TYPES.UPDATE_PERMISSION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PERMISSION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/permissions';

// Actions

export const getEntities: ICrudGetAllAction<IPermission> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PERMISSION_LIST,
  payload: axios.get<IPermission>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPermission> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PERMISSION,
    payload: axios.get<IPermission>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPermission> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PERMISSION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPermission> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PERMISSION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPermission> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PERMISSION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
