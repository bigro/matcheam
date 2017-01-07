package matcheam;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Created by ooguro on 2017/01/07.
 */
@NoArgsConstructor
@AllArgsConstructor
public class Identifier {

        private String value;

        @Override
        public String toString() {
                return value;
        }
}
