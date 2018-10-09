# Sample test project
This project demonstrate the spring boot microservices testing strategies using examples of JsonTest, DataJpaTest, RestClientTest and WebMvcTest.

We can split the application up into three distinct parts. The web part deals with the incoming requests and then returning the response. We have the domain part which is talking to the database, and the service package which is going to talk to the third party service.

# Testing Without Spring

Whenever possible, it's always best to try and write your tests without involving Spring. It's fast, it's easy to test things in isolation, and anyone can understand it. 
In order to facilitate testing without Spring, there are a couple of techniques that you can use. 

1. Always try to use constructor injection. That way you can just create mocks and create an instance of your object without needing Spring. 
2. Use Mockito as collaborator
3. Use AssertJ to test state
4. Use the ExpectedException @Rule if you want to check that a particular exception was thrown and to check that a particular message is present
5. Name tests consistently
6. Using BDDMockito, it's just a slightly different syntax. But this is saying given a call to use a repository, find by username, and any string I want you to return null. 


# Testing With Spring
Although testing without Spring is the ideal, sometimes you will need to involve Spring. You might be testing Spring-provided functionality, for example, Spring MVC or Spring Data, or you might be testing integrations that are provided by Spring like test things that are provided by AOP, for instance, maybe you want to check that the @Cacheables are working.

By default, SpringBootTest will try and find any SpringBoot configuration class. You can also override this. You can either add nested configuration classes, or you can use the classes attribute on the annotation.

SpringBootTest can start up various different web environments. Use the web environment attribute if you want to start a mock servlet environment or start a real server environment either on a defined port or a random port. If you're using a random port, you can use the TestRestTemplate or the @LocalServerPort annotation in order to find out which port was actually used.

if you're coming from an older version of Spring, you may have used SpringJUnit4ClassRunner. With Spring 4.3, this has been renamed, so you can just call it SpringRunner. It just makes your task classes a little bit more readable. 

SpringBootTest will search from the package that you're in upwards until it finds a SpringBoot application class, so, more precisely, til it finds a SpringBoot configuration annotated class. So, in our case, this is our main application class by virtue of the fact it's annotated with SpringBoot application.

TestRestTemplate allows you to make rest calls, but you don't need to specify the server or the port. 

 
# @MockBean

SpringBoot 1.4 introduces a new annotation called MockBean. It's very similar to @Mock, but instead of just injecting it into the test, it injects it into the spring application context. It also takes care of resetting the mock so there's no need for @DirtiesContext on your tests. It takes care of context caching issues and it deals with a lot of AOP issues that you can get if you've tried to use Mockito out the box.

There's also a SpyBean annotation which is available. So here we are back at our application integration test. 

we really want is a way to test our application without needing to call the real service. So rather than having to have the real rest service running every time our test runs, 

When the test runs this mock instance is injected both into the application context and to the test itself, so you can safely refer to this field directly.

# Test Application slices

Spring Boot introduces some new annotations that are specifically designed for testing application slices. 
JSON
Data JPA
Rest Client
Spring MVC


# Use @JsonTest

Typical REST application you're probably marshaling to and from JSON

It often makes sense to have specific tests just to make sure the objects are marshaling correctly. The JSON test annotation in Spring Boot 1.4 auto configures Jackson and it also auto configures GSON and provides access to JacksonTester and GSON testing classes. These allow you to check the structure of JSON.

1. Use to test JSON marshalling and unmarshalling
2. Auto-configures Jackson / GSON
3. Provide Jackson tester / Json Tester

# Use @DataJpaTest

1. The DataJpaTest annotation allows you to test domain logic so you can check JPA mappings and you can check queries
2. It also configures Hibernate, Spring Data and an in-memory database. 
3. You can override the in-memory database if you want to reuse a real one.
4. It also provides access to a TestEntityManager bean. This is an alternative to the regular entity manager that just provides some convenience methods that you often want to use in your tests

# Use @RestClientTest

1. The RestClientTest annotation allows you to test RestTemplate calls, as long as you've created them via the RestTemplateBuilder.
2. It auto-configures RestTemplate beans.
3. It provides Jackson, HttpMessageConverters
4. it also provides a MockRestServiceServer bean.
5. MockRestServiceServer is introduced in Spring 3.2 and it's a way of mocking out rest calls. You can configure it manually, but the new annotation just makes it a lot easier to use. Finally, we're actually injecting our service itself, so that we can do the calls that we wanna test.

# Use @WebMvcTest

1. The MVC test annotation allows you to test Spring MVC mappings
2. It will auto-configure all web components, 
3. Provide a MockMvc bean.
4. Optionally can be used with HTMLUnit or Selenium. 





