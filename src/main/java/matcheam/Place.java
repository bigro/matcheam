package matcheam;

import lombok.NoArgsConstructor;

/**
 * Created by ooguro on 2017/01/07.
 */
@NoArgsConstructor
public class Place {

        private String value;

        public Place(String value) {
                this.value = value;
        }

        @Override
        public String toString() {
                return value;
        }

}
