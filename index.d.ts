declare module '@apiverve/countrylookup' {
  export interface countrylookupOptions {
    api_key: string;
    secure?: boolean;
  }

  export interface countrylookupResponse {
    status: string;
    error: string | null;
    data: any;
    code?: number;
  }

  export default class countrylookupWrapper {
    constructor(options: countrylookupOptions);

    execute(callback: (error: any, data: countrylookupResponse | null) => void): Promise<countrylookupResponse>;
    execute(query: Record<string, any>, callback: (error: any, data: countrylookupResponse | null) => void): Promise<countrylookupResponse>;
    execute(query?: Record<string, any>): Promise<countrylookupResponse>;
  }
}
