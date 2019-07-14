package com.graphqljava.tutorial.bookdetails;

import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class GraphQLMutationFetcher {

    DataFetcher createBook = new DataFetcher() {
        @Override
        public Object get(DataFetchingEnvironment environment) throws Exception {
            String bookName = environment.getArgument("name");
            int pageCount = environment.getArgument("pageCount");
            Map<String, String> author = environment.getArgument("author");

            author.put("authorId", UUID.randomUUID().toString());
            GraphQLDataFetchers.authors.add(author);

            Map<String, String> newBook = ImmutableMap.of("id", UUID.randomUUID().toString(),
                    "name", bookName,
                    "pageCount", String.valueOf(pageCount),
                    "authorId", author.get("authorId"));
            GraphQLDataFetchers.books.add(newBook);
            return newBook.get("id");
        }
    };

}
