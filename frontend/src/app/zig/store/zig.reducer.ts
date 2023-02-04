import * as ZigActions from './zig.actions';

export interface State {
  requests: any[];
  request: any;
}

const initialState: State = {
  request: null,
  requests: [],
};

export function zigReducer(
  state = initialState,
  action: ZigActions.ZigActions
) {
  switch (action.type) {
    case ZigActions.SET_REQUESTS:
      return {
        ...state,
        requests: action.payload,
      };
    case ZigActions.SET_REQUEST:
      return {
        ...state,
        request: action.payload,
      };
    default:
      return state;
  }
}
