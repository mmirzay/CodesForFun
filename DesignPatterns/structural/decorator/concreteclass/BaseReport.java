package structural.decorator.concreteclass;

import structural.decorator.interfaces.Report;

public class BaseReport implements Report {

	@Override
	public String getColumns() {
		return "Row | ID";
	}

}
