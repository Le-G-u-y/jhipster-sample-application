import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IExcerciseConfig, defaultValue } from 'app/shared/model/excercise-config.model';

export const ACTION_TYPES = {
  FETCH_EXCERCISECONFIG_LIST: 'excerciseConfig/FETCH_EXCERCISECONFIG_LIST',
  FETCH_EXCERCISECONFIG: 'excerciseConfig/FETCH_EXCERCISECONFIG',
  CREATE_EXCERCISECONFIG: 'excerciseConfig/CREATE_EXCERCISECONFIG',
  UPDATE_EXCERCISECONFIG: 'excerciseConfig/UPDATE_EXCERCISECONFIG',
  DELETE_EXCERCISECONFIG: 'excerciseConfig/DELETE_EXCERCISECONFIG',
  RESET: 'excerciseConfig/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IExcerciseConfig>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ExcerciseConfigState = Readonly<typeof initialState>;

// Reducer

export default (state: ExcerciseConfigState = initialState, action): ExcerciseConfigState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_EXCERCISECONFIG_LIST):
    case REQUEST(ACTION_TYPES.FETCH_EXCERCISECONFIG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_EXCERCISECONFIG):
    case REQUEST(ACTION_TYPES.UPDATE_EXCERCISECONFIG):
    case REQUEST(ACTION_TYPES.DELETE_EXCERCISECONFIG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_EXCERCISECONFIG_LIST):
    case FAILURE(ACTION_TYPES.FETCH_EXCERCISECONFIG):
    case FAILURE(ACTION_TYPES.CREATE_EXCERCISECONFIG):
    case FAILURE(ACTION_TYPES.UPDATE_EXCERCISECONFIG):
    case FAILURE(ACTION_TYPES.DELETE_EXCERCISECONFIG):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_EXCERCISECONFIG_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_EXCERCISECONFIG):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_EXCERCISECONFIG):
    case SUCCESS(ACTION_TYPES.UPDATE_EXCERCISECONFIG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_EXCERCISECONFIG):
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

const apiUrl = 'api/excercise-configs';

// Actions

export const getEntities: ICrudGetAllAction<IExcerciseConfig> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_EXCERCISECONFIG_LIST,
  payload: axios.get<IExcerciseConfig>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IExcerciseConfig> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_EXCERCISECONFIG,
    payload: axios.get<IExcerciseConfig>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IExcerciseConfig> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_EXCERCISECONFIG,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IExcerciseConfig> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_EXCERCISECONFIG,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IExcerciseConfig> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_EXCERCISECONFIG,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
