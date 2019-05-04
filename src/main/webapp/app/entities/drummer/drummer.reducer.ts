import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDrummer, defaultValue } from 'app/shared/model/drummer.model';

export const ACTION_TYPES = {
  FETCH_DRUMMER_LIST: 'drummer/FETCH_DRUMMER_LIST',
  FETCH_DRUMMER: 'drummer/FETCH_DRUMMER',
  CREATE_DRUMMER: 'drummer/CREATE_DRUMMER',
  UPDATE_DRUMMER: 'drummer/UPDATE_DRUMMER',
  DELETE_DRUMMER: 'drummer/DELETE_DRUMMER',
  RESET: 'drummer/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDrummer>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type DrummerState = Readonly<typeof initialState>;

// Reducer

export default (state: DrummerState = initialState, action): DrummerState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DRUMMER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DRUMMER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DRUMMER):
    case REQUEST(ACTION_TYPES.UPDATE_DRUMMER):
    case REQUEST(ACTION_TYPES.DELETE_DRUMMER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DRUMMER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DRUMMER):
    case FAILURE(ACTION_TYPES.CREATE_DRUMMER):
    case FAILURE(ACTION_TYPES.UPDATE_DRUMMER):
    case FAILURE(ACTION_TYPES.DELETE_DRUMMER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DRUMMER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_DRUMMER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DRUMMER):
    case SUCCESS(ACTION_TYPES.UPDATE_DRUMMER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DRUMMER):
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

const apiUrl = 'api/drummers';

// Actions

export const getEntities: ICrudGetAllAction<IDrummer> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_DRUMMER_LIST,
  payload: axios.get<IDrummer>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IDrummer> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DRUMMER,
    payload: axios.get<IDrummer>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDrummer> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DRUMMER,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDrummer> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DRUMMER,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDrummer> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DRUMMER,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
