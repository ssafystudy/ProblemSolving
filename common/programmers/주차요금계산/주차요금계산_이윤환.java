import java.util.*;

class Solution {
    static class Record {
        String carNumber;
        int fee;

        public Record(String carNumber, int fee) {
            this.carNumber = carNumber;
            this.fee = fee;
        }
    }

    public int[] solution(int[] fees, String[] records) {

        Map<String, Integer> parkingTimeMap = new HashMap<>();
        Map<String, Integer> recordMap = new HashMap<>();
        for (String record : records) {
            String[] temp = record.split(" ");
            String carNumber = temp[1];

            if (recordMap.containsKey(carNumber)) {
                int in = recordMap.get(temp[1]);
                int out = covertTime(temp[0]);
                if (parkingTimeMap.containsKey(carNumber)) {
                    parkingTimeMap.put(carNumber, parkingTimeMap.get(carNumber) + out - in);
                } else {
                    parkingTimeMap.put(carNumber, out - in);
                }
                recordMap.remove(carNumber);
            } else {
                recordMap.put(carNumber, covertTime(temp[0]));
            }
        }

        int endTime = covertTime("23:59");
        Set<Map.Entry<String, Integer>> remains = recordMap.entrySet();
        for (Map.Entry<String, Integer> remain : remains) {
            String carNumber = remain.getKey();
            int in = remain.getValue();
            if (parkingTimeMap.containsKey(carNumber)) {
                parkingTimeMap.put(carNumber, parkingTimeMap.get(carNumber) + endTime - in);
            } else {
                parkingTimeMap.put(carNumber, endTime - in);
            }
        }

        int basicTime = fees[0];
        int basicFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];

        return parkingTimeMap.entrySet().stream()
                .sorted((o1, o2) -> o1.getKey().compareTo(o2.getKey()))
                .mapToInt(o -> (int) (basicFee + Math.ceil(((double) o.getValue() - basicTime < 0 ? 0 : (double) o.getValue() - basicTime) / unitTime) * unitFee))
                .toArray();
    }

    private int covertTime(String time) {
        String[] temp = time.split(":");
        return Integer.parseInt(temp[0]) * 60 + Integer.parseInt(temp[1]);
    }
}