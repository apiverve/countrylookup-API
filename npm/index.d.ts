declare module '@apiverve/countrylookup' {
  export interface countrylookupOptions {
    api_key: string;
    secure?: boolean;
  }

  export interface countrylookupResponse {
    status: string;
    error: string | null;
    data: CountryLookupData;
    code?: number;
  }


  interface CountryLookupData {
      search:    string;
      countries: Country[];
  }
  
  interface Country {
      name:         Name;
      tld:          string[];
      cca2:         string;
      ccn3:         string;
      cca3:         string;
      cioc:         string;
      independent:  boolean;
      status:       string;
      currencies:   Currencies;
      capital:      string[];
      altSpellings: string[];
      region:       string;
      subregion:    string;
      languages:    Languages;
      latlng:       number[];
      landlocked:   boolean;
      borders:      string[];
      area:         number;
      flag:         string;
      majorCities:  string[];
  }
  
  interface Currencies {
      usd: Usd;
  }
  
  interface Usd {
      name:   string;
      symbol: string;
  }
  
  interface Languages {
      eng: string;
  }
  
  interface Name {
      common:   string;
      official: string;
      native:   Native;
  }
  
  interface Native {
      eng: Eng;
  }
  
  interface Eng {
      official: string;
      common:   string;
  }

  export default class countrylookupWrapper {
    constructor(options: countrylookupOptions);

    execute(callback: (error: any, data: countrylookupResponse | null) => void): Promise<countrylookupResponse>;
    execute(query: Record<string, any>, callback: (error: any, data: countrylookupResponse | null) => void): Promise<countrylookupResponse>;
    execute(query?: Record<string, any>): Promise<countrylookupResponse>;
  }
}
