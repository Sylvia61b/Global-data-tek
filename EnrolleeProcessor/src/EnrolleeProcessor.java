import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/*Availlty receives enrollment files from various benefits management and enrollment solutions.
Most of these files are typically in EDI format.
However, there are some files in CVS format.
For the files in CSV format , write a program that will read the content of the file and separate enrollees by insurance company in its own file.
Additionally, sort the contents of each file by last and first name(ascending).
Lastly , if there are duplicate User Ids for the same Insurance Company,
then Only the record with the highest version should be included.
The following data points are included in the file:
User Id(String)
First Name(String)
Last Name (String)
version(Integer)
Insurance Company(String)
 */


public class EnrolleeProcessor {

    public static void main(String[] args) {
        String csvFilePath = "data.csv";
        String outputDirectory = "output";

        try {
            processEnrollees(csvFilePath, outputDirectory);
            System.out.println("Processing complete.");

        } catch (IOException e) {
            System.err.println("Error reading or writing files: " + e.getMessage());
        }
    }

    private static void processEnrollees(String csvFilePath, String outputDirectory) throws IOException {
        // Map to store enrollees by userId (unique)
        Map<String, TreeSet<Enrollee>> enrolleesByCompany = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath, StandardCharsets.UTF_8))) {
            // Skip header line
            String line = reader.readLine();


            // Read each line from CSV file
            while ((line = reader.readLine()) != null) {
                // Split by comma, -1 to handle trailing empty fields
                String[] fields = line.split(",", -1);

                // Extract fields
                String userId = fields[0];
                String firstName = fields[1];
                String lastName = fields[2];
                int version = Integer.parseInt(fields[3]);
                String insuranceCompany = fields[4];

                // Create Enrollee object
                Enrollee enrollee = new Enrollee(userId, firstName, lastName, version, insuranceCompany);

                // Add enrollee to the map for this insurance company
                TreeSet<Enrollee> enrolleesForCompany = enrolleesByCompany.computeIfAbsent(insuranceCompany, k -> new TreeSet<>(Comparator.comparing(Enrollee::getUserId)));
                // Remove lower version of duplicate enrollees
                enrolleesForCompany.removeIf(e -> e.getUserId().equals(userId) && e.getVersion() < version);
                // Add new enrollee
                enrolleesForCompany.add(enrollee);
            }

            // Process each company's enrollees
            for (Map.Entry<String, TreeSet<Enrollee>> entry : enrolleesByCompany.entrySet()) {
                String company = entry.getKey();
                TreeSet<Enrollee> enrollees = entry.getValue();

                // Sort enrollees by first name, then by last name (ascending)
                // Convert to list for sorting
                List<Enrollee> sortedEnrollees = new ArrayList<>(enrollees);
                sortedEnrollees.sort(Comparator.comparing(Enrollee::getFirstName).thenComparing(Enrollee::getLastName));

                // Write sorted enrollees to a new CSV file for this insurance company
                String outputFile = outputDirectory + "/" + company + "_enrollees.csv";
                writeEnrolleesToCSV(sortedEnrollees, outputFile);
            }

            System.out.println("Processing complete.");

        } catch (IOException e) {
            System.err.println("Error processing enrollees: " + e.getMessage());
            // Re-throw the exception to propagate it up
            throw e;
        }
    }

    // Method to write enrollees to a CSV file
    private static void writeEnrolleesToCSV(List<Enrollee> enrollees, String filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(filePath, StandardCharsets.UTF_8)) {
            // Write CSV header
            writer.println("User Id,First Name,Last Name,Version,Insurance Company");

            // Write each enrollee
            for (Enrollee enrollee : enrollees) {
                writer.println(enrollee.toCSVString());
            }
        } catch (FileNotFoundException e) {
            throw new IOException("Error creating output file: " + filePath, e);
        }
    }

    // Enrollee class representing data points from CSV
    private static class Enrollee {
        private String userId;
        private String firstName;
        private String lastName;
        private int version;
        private String insuranceCompany;

        public Enrollee(String userId, String firstName, String lastName, int version, String insuranceCompany) {
            this.userId = userId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.version = version;
            this.insuranceCompany = insuranceCompany;
        }

        public String getUserId() {
            return userId;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public int getVersion() {
            return version;
        }

        public String getInsuranceCompany() {
            return insuranceCompany;
        }

        // Method to represent Enrollee as a CSV string
        public String toCSVString() {
            return String.format("%s,%s,%s,%d,%s", userId, firstName, lastName, version, insuranceCompany);
        }
    }
}
