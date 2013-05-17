/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.xd.dirt.stream;

import static org.junit.Assert.fail;

import org.junit.Test;

import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.xd.dirt.launcher.RedisContainerLauncher;
import org.springframework.xd.dirt.stream.RedisStreamDeployer;
import org.springframework.xd.dirt.stream.StreamServer;
/**
 * @author Winston Koh
 * @since 1.0
 *
 */
public class StreamServerTests {

	@Test
        public void testSimpleServer() {
		LettuceConnectionFactory connectionFactory = null;
		try {
                        RedisContainerLauncher.main(new String[0]);
			connectionFactory = new LettuceConnectionFactory();
			connectionFactory.afterPropertiesSet();
                        RedisStreamDeployer streamDeployer = new RedisStreamDeployer(connectionFactory);
                        StreamServer server = new StreamServer(streamDeployer);
                        server.afterPropertiesSet();
                        server.start();
                        server.stop();
		}
		catch (Exception e) {
			fail("STREAM SERVER IS NOT AVAILABLE");
		}
		finally {
			if (connectionFactory != null) {
				connectionFactory.destroy();
			}
		}
	}

}
