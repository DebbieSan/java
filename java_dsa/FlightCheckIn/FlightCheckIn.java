
public class FlightCheckIn {

    static class Data {
        int priorityNumber;
        String passengerName;

        Data(int priorityNumber, String passengerName) {
            this.priorityNumber = priorityNumber;
            this.passengerName = passengerName;
        }

    }

    public static class FlightSet {
        public static final int TABLE_SIZE = 13; // this is where the k mod 13 comes in
        private Data[] table = new Data[TABLE_SIZE];

        // hash function k mod 13
        private int hash(int key) {
            return key % TABLE_SIZE;
        }

        public void add(int priorityNumber, String passengerName) {
            int index = hash(priorityNumber);

            // check if the priority number is already present in the upgrade list,
            // otherwise we will need to prevent collision
            // using linear probing
            for (int i = 0; i < TABLE_SIZE; i++) {
                int probeIndex = (index + i) % TABLE_SIZE;
                Data current = table[probeIndex];

                if (current == null) {
                    // empty slot found – insert new passenger into the priority upgrade list
                    table[probeIndex] = new Data(priorityNumber, passengerName);
                    return;
                }

                if (current.priorityNumber == priorityNumber) {
                    // priorityNumber exists - update passenger name (optional)
                    current.passengerName = passengerName;
                    return;
                }
                // otherwise keep probing to the next index (linear probing)
            }

            // if we get here, every slot in the table is occupied - the priority list is
            // full
            System.out.println(
                    "Priority upgrade list is full, unable to add passenger with priority number: "
                            + priorityNumber);
        }

        // A method to check if priority list is full
        public boolean isPriorityListFull() {
            for (int i = 0; i < TABLE_SIZE; i++) {
                if (table[i] == null)
                    return false;
            }
            return true;
        }

        // Check if a priority number exists
        public boolean exists(int priorityNumber) {
            int index = hash(priorityNumber);
            for (int i = 0; i < TABLE_SIZE; i++) {
                int probeIndex = (index + i) % TABLE_SIZE;
                Data current = table[probeIndex];

                if (current == null) {
                    // if we hit an empty slot, the key is not in the table
                    return false;
                }

                if (current.priorityNumber == priorityNumber) {
                    return true;
                }
            }
            return false; // table full but not found
        }

        // method to print table to verify collisions and any issues that can be seeing
        // visually
        public void printSet() {
            System.out.println(); // blank line
            System.out.println("====== HashSet Table displaying list of passengers ======");
            System.out.println(); // blank line

            for (int i = 0; i < TABLE_SIZE; i++) {
                System.out.print(i + ": ");
                Data current = table[i];
                if (current == null) {
                    System.out.println("no data available");
                } else {
                    System.out.println("[" + current.priorityNumber +
                            " - " + current.passengerName + "]");
                }
            }
        }

    }
}
