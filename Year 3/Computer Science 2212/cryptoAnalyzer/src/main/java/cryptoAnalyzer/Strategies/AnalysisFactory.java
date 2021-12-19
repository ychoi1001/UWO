package cryptoAnalyzer.Strategies;

/**
 * This is a class for analysis factory
 * @author gracezhu
 *
 */
public class AnalysisFactory {

	/**
	 * create strategy based on analysis type
	 * @param analysis analysis type
	 * @return corresponding strategy
	 */
	public Strategy create(String analysis) {
		if(analysis.equals("Price")) {
			return new PriceStrategy();
		
		}else if(analysis.equals("MarketCap")) {
			return new MarketCapStrategy();
		
		}else if(analysis.equals("Volume")) {
			return new VolumeStrategy();
		
		}else if(analysis.equals("Coins in Circulation")) {
			return new CICStrategy();
		
		}else if(analysis.equals("Percent Change of Price")) {
			return new PerPriceStrategy();
		
		}else if(analysis.equals("Percent Change of MarketCap")) {
			return new PerMarketCapStrategy();
		
		}else if(analysis.equals("Percent Change of Volume")) {
			return new PerVolumeStrategy();
		
		}else if(analysis.equals("Percent Change of Coins in Circulation")) {
			return new PerCICStrategy();
		}else {
			return null;
		}
	}
}
