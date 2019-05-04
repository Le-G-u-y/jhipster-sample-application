import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDrummerStatistics, defaultValue } from 'app/shared/model/drummer-statistics.model';

export const ACTION_TYPES = {
  FETCH_DRUMMERSTATISTICS_LIST: 'drummerStatistics/FETCH_DRUMMERSTATISTICS_LIST',
  FETCH_DRUMMERSTATISTICS: 'drummerStatistics/FETCH_DRUMMERSTATISTICS',
  CREATE_DRUMMERSTATISTICS: 'drummerStatistics/CREATE_DRUMMERSTATISTICS',
  UPDATE_DRUMMERSTATISTICS: 'drummerStatistics/UPDATE_DRUMMERSTATISTICS',
  DELETE_DRUMMERSTATISTICS: 'drummerStatistics/DELETE_DRUMMERSTATISTICS',
  RESET: 'drummerStatistics/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDrummerStatistics>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type DrummerStatisticsState = Readonly<typeof initialState>;

// Reducer

export default (state: DrummerStatisticsState = initialState, action): DrummerStatisticsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DRUMMERSTATISTICS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DRUMMERSTATISTICS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DRUMMERSTATISTICS):
    case REQUEST(ACTION_TYPES.UPDATE_DRUMMERSTATISTICS):
    case REQUEST(ACTION_TYPES.DELETE_DRUMMERSTATISTICS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DRUMMERSTATISTICS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DRUMMERSTATISTICS):
    case FAILURE(ACTION_TYPES.CREATE_DRUMMERSTATISTICS):
    case FAILURE(ACTION_TYPES.UPDATE_DRUMMERSTATISTICS):
    case FAILURE(ACTION_TYPES.DELETE_DRUMMERSTATISTICS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DRUMMERSTATISTICS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_DRUMMERSTATISTICS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DRUMMERSTATISTICS):
    case SUCCESS(ACTION_TYPES.UPDATE_DRUMMERSTATISTICS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DRUMMERSTATISTICS):
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

const apiUrl = 'api/drummer-statistics';

// Actions

export const getEntities: ICrudGetAllAction<IDrummerStatistics> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_DRUMMERSTATISTICS_LIST,
  payload: axios.get<IDrummerStatistics>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IDrummerStatistics> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DRUMMERSTATISTICS,
    payload: axios.get<IDrummerStatistics>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDrummerStatistics> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DRUMMERSTATISTICS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDrummerStatistics> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DRUMMERSTATISTICS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDrummerStatistics> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DRUMMERSTATISTICS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
