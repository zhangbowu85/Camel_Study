package bw.study.examples.camel

import org.jsmiparser.parser.SmiDefaultParser
import org.jsmiparser.smi.SmiMib
import org.jsmiparser.util.url.FileURLListFactory
import sun.net.www.protocol.file.FileURLConnection

class MibDB {

    public static void main(args) {

        SmiDefaultParser parser = new SmiDefaultParser()

        String[] mibDirs = ['/tmp/upm_mibs/iana', '/tmp/upm_mibs/ietf', '/tmp/upm_mibs/samsung']
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
        parser.setFailOnError(false)
        parser.getFileParserPhase().setInputUrls(mibLibUrls)
        SmiMib mib = parser.parse()

        if (mib != null) {
            System.out.println('Succeed')
            mib.getColumns().each {
                if (it.oidStr.contains('236')) {
                    System.out.println("${it.oidStr}, ${it.type}, ${it.toString()}")
                }
            }
        } else {
            System.out.println("Failed to load mibs.")
        }

    }
}
