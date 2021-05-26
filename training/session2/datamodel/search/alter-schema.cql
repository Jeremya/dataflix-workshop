ALTER SEARCH INDEX SCHEMA ON videos 
ADD types.fieldtype[@class='org.apache.solr.schema.TextField', @name='TextField'] 
WITH '{"analyzer":
          { "tokenizer":{"class":"solr.StandardTokenizerFactory"}, 
            "filter":   [{"class": "solr.StandardFilterFactory"}, 
                         {"class": "solr.LowerCaseFilterFactory"},
                         {"class": "solr.StopFilterFactory"}
                       ]
          }
      }';

ALTER SEARCH INDEX SCHEMA ON videos SET field[@name='mpaa_rating']@type = 'TextField';
ALTER SEARCH INDEX SCHEMA ON videos SET field[@name='type']@type = 'TextField';
ALTER SEARCH INDEX SCHEMA ON videos SET field[@name='url']@type = 'TextField';
ALTER SEARCH INDEX SCHEMA ON videos SET field[@name='title']@type = 'TextField';
ALTER SEARCH INDEX SCHEMA ON videos SET field[@name='description']@type = 'TextField';
ALTER SEARCH INDEX SCHEMA ON videos SET field[@name='tags']@type = 'TextField';

RELOAD SEARCH INDEX ON videos;
REBUILD SEARCH INDEX ON videos;
