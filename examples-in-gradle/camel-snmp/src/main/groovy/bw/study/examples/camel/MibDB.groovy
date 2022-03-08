package bw.study.examples.camel

import org.wintersleep.snmp.mib.parser.SmiDefaultParser
import org.wintersleep.snmp.mib.phase.file.FileParserPhase
import org.wintersleep.snmp.mib.smi.SmiMib
import org.wintersleep.snmp.util.url.FileURLListFactory

class MibDB {

    public static void main(args) {

        SmiDefaultParser parser = new SmiDefaultParser()
        parser.setFailOnError(false)

        String[] mibDirs = ['c:/mibtest/iana', 'c:/mibtest/ietf', 'c:/mibtest/cardinality-upm', 'c:/mibtest/samsung']
        List<URL> mibLibUrls = new LinkedList<URL>()
        mibDirs.each {
            File dir = new File(it)
            FileURLListFactory urlListFactory = new FileURLListFactory(dir);
            dir.listFiles().each {
                if (it.isFile()) {
                    //System.out.println(it.getName())
                    urlListFactory.add(it.getName())
                }
            }
            mibLibUrls.addAll(urlListFactory.create())
        }
        System.out.println(mibLibUrls.toString())
        parser.setFailOnError(true)
        parser.getFileParserPhase().setInputUrls(mibLibUrls)
        SmiMib mib = parser.parse()

        if (mib != null) {
            System.out.println('Succeed')
            mib.getColumns().findAll({
               it.getOidStr().contains('Cardinality') || it.getOidStr().contains('SAMSUNG') || true
            }).each {
                System.out.println("${it.oidStr}, ${it.type}, ${it.toString()}")
            }
        } else {
            System.out.println("Failed to load mibs.")
        }

    }
}
