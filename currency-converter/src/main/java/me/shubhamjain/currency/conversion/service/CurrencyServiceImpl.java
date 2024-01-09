package me.shubhamjain.currency.conversion.service;

import me.shubhamjain.currency.conversion.dto.CurrencyDTO;

import java.math.BigDecimal;

public class CurrencyServiceImpl implements CurrencyService {
    // https://gist.github.com/Ronin-Azriel/2401970
    // Questions to Ask
    @Override
    public BigDecimal convert(CurrencyDTO currencyDTO) {
        return BigDecimal.ZERO;
//        ObjectMapper mapper = new ObjectMapper();
//        JSONObject obj = null;
//        CurrencyConversion conversion = null;
//        try {
////            StringBuilder sb = new StringBuilder("https://free.currconv.com/api/v7/convert?q=");
////            sb.append(currencyDTO.getCurrencyFrom() + "_" + currencyDTO.getCurrencyTo());
////            sb.append("&compact=ultra&apiKey=7c99ae9a06fc46f62bb6");
//            StringBuilder sb = new StringBuilder("https://www.google.com/ig/calculator?hl=en");
//            sb.append("&q=" + currencyDTO.getAmount() + currencyDTO.getCurrencyFrom() + "=?" + currencyDTO.getCurrencyTo());
//            URL url = new URL(sb.toString());
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            String inputLine;
//            StringBuffer sb = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                sb.append(inputLine);
//            }
//
//
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(con.getInputStream()));
//            String inputLine;
//            StringBuffer content = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                content.append(inputLine);
//            }
//            in.close();
//            System.out.println(content.toString());
//            if (content.toString().equals("{}")) {
//                System.out.println("Invalid Currency Code Sent " + currencyDTO.getCurrencyFrom() + " " + currencyDTO.getCurrencyTo());
//                return BigDecimal.ZERO;
//            }
//            obj = new JSONObject(content.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return currencyDTO.getAmount().multiply(obj.getBigDecimal(currencyDTO.getCurrencyFrom() + "_" + currencyDTO.getCurrencyTo()));
    }
}
